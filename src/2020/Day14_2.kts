import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2

var mask = ""
var program = HashMap<Long,Long>()
File("input/2020/day14").forEachLine {
    var (cmd,value) = it.split("=")
    when (cmd.trim()) {
        "mask" -> mask = value.trim()
        else -> write(cmd.trim(),value.trim(),mask)
    }
}
println(program.values.sum())

fun write(cmd: String, value: String, mask: String) {
    val input = cmd.removeSurrounding("mem[","]").toBitString()
    var masked = ""
    mask.forEachIndexed { index, it -> masked += if (it != '0') it else input.get(index) }
    ArrayList<String>().apply { replaceX(masked) }.forEach { program.put(it.bitsToLong(), value.toLong()) }
}

fun ArrayList<String>.replaceX(input: String) {
    if (input.count { it == 'X' } == 0) return
    val a = input.replaceFirst('X', '0')
    val b = input.replaceFirst('X', '1')
    if (a.count { it == 'X' } == 0) add(a)
    if (b.count { it == 'X' } == 0) add(b)
    replaceX(a)
    replaceX(b)
}

fun String.bitsToLong() = replaceRange(0,this.indexOf('1'),"").toLong(2)
fun String.toBitString() = this.toLong().toString(2).padStart(36,'0')