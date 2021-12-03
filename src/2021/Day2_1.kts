import java.io.File

var depth = 0
var horizontal = 0

File("input/2021/day2").apply {
    forEachLine { line ->
        val (code, change) = line.split(" ")
        when (code) {
            "forward" -> horizontal += change.toInt()
            "down" -> depth += change.toInt()
            "up" -> depth -= change.toInt()
        }
    }
}
println(depth*horizontal)