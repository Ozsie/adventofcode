import java.io.File

var increases = 0

File("input/2021/day1").apply {
    var prev = Int.MAX_VALUE
    forEachLine { line ->
        val depth = line.toInt()
        if (depth > prev) increases++
        prev = depth
    }
}
println(increases)