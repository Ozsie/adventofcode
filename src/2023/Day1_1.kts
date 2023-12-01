import java.io.File

val numbers = mutableListOf<String>()

File("input/2023/day1").apply {
    forEachLine { line ->
        val digits = line.filter { it.isDigit() }
        numbers.add("${digits.first()}${digits.last()}")
    }
}
println(numbers.map { it.toInt() }.sumOf { it })
