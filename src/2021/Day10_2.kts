import java.io.File

val matches = mapOf('[' to ']', '{' to '}', '(' to ')', '<' to '>')
val completionCharPoints = mapOf(')' to 1, ']' to 2, '}' to 3,'>' to 4)

fun String.firstIllegalCharOrNull(): Char? {
    val stack = mutableListOf<Char>()
    this.forEach {
        if (it in "([{<") stack.add(it)
        else if (stack.isNotEmpty() && matches.getValue(stack.removeLast()) != it) return it
    }
    return null
}

fun String.completionString(): String {
    val stack = mutableListOf<Char>()
    this.forEach {
        if (it in "([{<") stack.add(it)
        else if (stack.isNotEmpty()) stack.removeLast()
    }
    return buildString {
        while( stack.isNotEmpty() ) {
            append(matches.getValue(stack.removeLast()))
        }
    }
}

fun <E> List<E>.middle(): E = this[size.div(2)]

fun String.completionScore() = this.fold(0L) { score, char ->
    score * 5 + completionCharPoints.getValue(char)
}

val input = ArrayList<String>()
File("input/2021/day10").forEachLine { input.add(it) }
val sum = input.filter {
    it.firstIllegalCharOrNull() == null
}.map { it.completionString().completionScore() }.sorted().middle()
println(sum)