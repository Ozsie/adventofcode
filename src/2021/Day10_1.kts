import java.io.File

val matches = mapOf('[' to ']', '{' to '}', '(' to ')', '<' to '>')
val illegalCharPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

fun String.firstIllegalCharOrNull(): Char? {
    val stack = mutableListOf<Char>()
    this.forEach {
        if (it in "([{<") stack.add(it)
        else if (stack.isNotEmpty() && matches.getValue(stack.removeLast()) != it) return it
    }
    return null
}

val input = ArrayList<String>()
File("input/2021/day10").forEachLine { input.add(it) }
val sum = input.mapNotNull {
    it.firstIllegalCharOrNull()
}.sumOf { illegalCharPoints.getValue(it) }
println(sum)