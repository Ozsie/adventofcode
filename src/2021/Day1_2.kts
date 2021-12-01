import java.io.File

var prev = Int.MAX_VALUE
var increases = 0
var index = 0
val window = 3
var windows = ArrayList<Int>()

File("input/2021/day1").apply {
    forEachLine { line ->
        fun sum(index: Int, depth: Int): Int {
            return windows.getOrElse(index) {
                windows.add(0)
                0
            } + depth
        }
        val depth = line.toInt()
        val s1 = sum(index, depth)
        windows[index] = s1
        if (index - 1 >= 0) {
            val s2 = sum(index - 1, depth)
            windows[index-1] = s2
        }
        if (index - 2 >= 0) {
            val s3 = sum(index - 2, depth)
            windows[index-2] = s3
        }
        index++
    }
}
windows.subList(0, windows.size - 2).forEach { depth ->
    if (depth > prev) increases++
    prev = depth
}

println(increases)