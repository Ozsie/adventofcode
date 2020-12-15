import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2

data class MemoryNumber(var lastCall: Int = 0, var secondToLastCall: Int = 0, var calls: Int = 0)
fun MemoryNumber.update(turn: Int) {
    calls++
    secondToLastCall = lastCall
    lastCall = turn
}
var input = HashMap<Int, MemoryNumber>()
File("input/2020/day15").forEachLine {
    it.split(",").map { it.trim().toInt() }
            .forEachIndexed { i, n -> input.put(n, MemoryNumber(i + 1, 0,1))  }
}
var current = input.keys.last()
var previous = 0
for (index in (input.keys.size)..(2020-1)) {
    val turn = index + 1
    val mem = input.getOrElse(current) { input.get(previous) } ?: break
    previous = current
    if (mem.calls == 1) {
        val new = input.get(0) ?: break
        new.update(turn)
        current = 0
    } else {
        val i = mem.lastCall - mem.secondToLastCall
        val new = input.getOrElse(i) { MemoryNumber(turn, 1, 1) } ?: break
        new.update(turn)
        current = i
        input.put(i, new)
    }
}
println(current)