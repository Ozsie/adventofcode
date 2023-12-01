import java.io.File
import kotlin.math.min

val hexMap = mapOf(
    '0' to "0000",
    '1' to "0001",
    '2' to "0010",
    '3' to "0011",
    '4' to "0100",
    '5' to "0101",
    '6' to "0110",
    '7' to "0111",
    '8' to "1000",
    '9' to "1001",
    'A' to "1010",
    'B' to "1011",
    'C' to "1100",
    'D' to "1101",
    'E' to "1110",
    'F' to "1111"
)

fun String.decode() = map { hexMap.get(it) }.joinToString("")
fun String.extract(): String {
    val version = substring(0..2).toInt(2)
    totalVersion += version
    val type = substring(3..5).toInt(2)
    print("V$version T$type: ")
    return when(type) {
        4 -> substring(6).extractLiteral().second
        else -> substring(6).extractOperator()
    }
}
fun String.extractLiteral(): Pair<Long,String> {
    println("LIT> $this ")
    var literal = ""
    var remainder = this
    for (i in 0..length-1 step 5) {
        val chunk = substring(i,i+5)
        println("       CHK> ${chunk[0]} ${chunk.substring(1)}")
        literal = "$literal${chunk.substring(1)}"
        remainder = remainder.substring(min(i+5, remainder.length-1))
        if (chunk[0] == '0') break
    }
    println("       VAL> ${literal.toLong(2)}")
    return Pair(literal.toLong(2), remainder)
}
fun String.extractOperator(): String {
    val lengthType = if (first() == '0') 15 else 11
    println("OPR> ${first()} ${substring(1,lengthType+1)} ${substring(lengthType+1)}")
    println("       LT> $lengthType")
    if (lengthType == 15) {
        val subPacketLength = substring(1,lengthType+1).toInt(2)
        val subPackets = substring(lengthType+1).substring(0, subPacketLength)
        println("       15> $subPacketLength $subPackets")
        return subPackets.extract()
    } else {
        val packetCount = substring(1,lengthType+1).toInt(2)
        val subPackets = substring(lengthType+1)
        var remainder = subPackets
        val packetLength = 11
        println("       11> $packetCount $subPackets")
        print("         >  ")
        var packetsRead = 0
        for (i in 0..subPackets.length-1 step packetLength) {
            print(" ${subPackets.substring(i,i+packetLength)}")
            if (++packetsRead == packetCount) break
        }
        println()
        packetsRead = 0
        for (i in 0..subPackets.length-1 step packetLength) {
            subPackets.substring(i,i+packetLength).extract()
            if (++packetsRead == packetCount) {
                remainder = subPackets.substring(i+packetLength)
                break
            }
        }
        return remainder
    }
}
println()
var totalVersion = 0
File("input/2021/day16").forEachLine { line ->
    totalVersion = 0
    var remainder = line.decode()
    while (remainder.length > 6 && remainder.any { it == '1' }) {
        println("       TOP> $remainder")
        remainder = remainder.extract()
    }
    println(totalVersion)
}

//"0011111010010000".extract()