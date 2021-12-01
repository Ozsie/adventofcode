import java.io.File

var windows = ArrayList<Int>()

fun ArrayList<Int>.sum(index: Int, depth: Int): Int = getOrElse(index) {
    add(0)
    0
} + depth

fun ArrayList<Int>.updateIndex(index: Int, depth: Int) {
    if (index >= 0) this[index] = sum(index, depth)
}

fun ArrayList<Int>.countIncreases(): Int {
    var increases = 0
    var prev = Int.MAX_VALUE
    subList(0, size - 2).forEach { depth ->
        if (depth > prev) increases++
        prev = depth
    }
    return increases
}

File("input/2021/day1").apply {
    var index = 0
    forEachLine { line ->
        val depth = line.toInt()
        windows.updateIndex(index, depth)
        windows.updateIndex(index - 1, depth)
        windows.updateIndex(index - 2, depth)
        index++
    }
}

println(windows.countIncreases())