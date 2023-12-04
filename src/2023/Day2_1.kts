import java.io.File

val bag = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

var sum = 0

File("input/2023/day2").apply {
    forEachLine { line ->
        val id = line.split(":")[0].trim().substring(5).toInt()
        val results = line.split(":")[1].trim()
        val possible = results.split(";")
            .filter { it.isNotEmpty() }
            .map { it.trim().split(",") }
            .map {
                it.map {
                    val parts = it.trim().split(" ")
                    parts[1].trim() to parts[0].trim().toInt()
                }
            }.all {
                it.all { draw -> draw.second <= (bag[draw.first] ?: 0) }
            }
        if (possible) sum += id
    }
}
println(sum)
