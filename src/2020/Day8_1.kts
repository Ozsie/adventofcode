import java.io.File
import kotlin.collections.ArrayList

data class Instruction(val name: String, val arg: Int, var called: Boolean = false)
val instructions = ArrayList<Instruction>()
File("input/2020/day8").forEachLine {
    val (i,a) = it.split(" ")
    instructions.add(Instruction(i,a.toInt()))
}

var accumulator = 0
var index = 0
while (index < instructions.size) {
    val i = instructions[index]
    if (i.called) {
        println("Terminate at: $index> $i")
        println(accumulator)
        System.exit(0)
    }
    i.called = true

    index = if (i.name == "jmp") {
        index + i.arg
    } else {
        if (i.name == "acc") accumulator += i.arg
        index + 1
    }
}