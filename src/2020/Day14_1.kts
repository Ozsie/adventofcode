import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2

var mask = ""
var program = HashMap<String,Long>()
File("input/2020/day14").forEachLine {
    var (cmd,value) = it.split("=")
    when (cmd.trim()) {
        "mask" -> mask = value.trim()
        else -> write(cmd.trim(),value.trim(),mask)
    }
}
var sum = program.values.sum()
println(sum)

fun write(cmd: String, value: String, mask: String) {
    val address = cmd.removeSurrounding("mem[","]")
    val input = value.toBitString()
    var masked = ""
    mask.forEachIndexed { index, it -> masked += if (it != 'X') it else input.get(index) }
    program.put(address,masked.bitsToLong())
}

fun String.bitsToLong() = replaceRange(0,this.indexOf('1'),"").toLong(2)
fun String.toBitString() = this.toLong().toString(2).padStart(36,'0')