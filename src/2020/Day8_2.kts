import java.io.File
import kotlin.collections.ArrayList

data class Instruction(var name: String, val arg: Int, var called: Boolean = false)
val instructions = ArrayList<Instruction>()
File("input/2020/day8").forEachLine {
    val (i,a) = it.split(" ")
    instructions.add(Instruction(i,a.toInt()))
}

fun run(): Boolean {
    var accumulator = 0
    var index = 0
    while (index < instructions.size) {
        val i = instructions[index]
        if (i.called) return false else i.called = true

        index = if (i.name == "jmp") {
            index + i.arg
        } else {
            if (i.name == "acc") accumulator += i.arg
            index + 1
        }
    }
    println(accumulator)
    return true
}

instructions.filter { it.name != "acc" }
        .forEach {
            it.name = if (it.name == "nop") "jmp" else "nop"
            val success = run()
            if (success) System.exit(0) else instructions.forEach { it.called = false }
            it.name = if (it.name == "nop") "jmp" else "nop"
        }