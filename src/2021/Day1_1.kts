import java.io.File

var prev = Int.MAX_VALUE
var increases = 0

File("input/2021/day1").apply {
    forEachLine { line ->
        val depth = line.toInt()
        if (depth > prev) increases++
        prev = depth
    }
}
println(increases)