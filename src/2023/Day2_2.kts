import java.io.File

val bag = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

var sum = 0

File("input/2023/day2").apply {
    forEachLine { line ->
        val maxes = mutableMapOf<String, Int>()
        line.split(":")[1].trim()
            .split(";")
            .filter { it.isNotEmpty() }
            .flatMap { it.trim().split(",") }
            .map { it.trim().split(" ") }
            .map { it[1] to it[0] }
            .groupBy { it.first }
            .flatMap { it.value }
            .forEach {
                if (maxes[it.first] == null || it.second.toInt() > (maxes[it.first] ?: 0)) {
                    maxes[it.first] = it.second.toInt()
                }
            }
        sum += maxes.map { it.value }.reduce { acc, s -> acc*s }
    }
}
println(sum)
