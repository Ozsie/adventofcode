import java.io.File

var depth = 0
var horizontal = 0
var aim = 0

File("input/2021/day2").apply {
    forEachLine { line ->
        val (code, value) = line.split(" ")
        val change = value.toInt()
        when (code) {
            "forward" -> {
                horizontal += change
                depth += (change * aim)
            }
            "down" -> {
                aim += change
            }
            "up" -> {
                aim -= change
            }
        }
    }
}
println(depth*horizontal)