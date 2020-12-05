import java.io.File

fun File.slope(xStep: Int, yStep: Int): Long {
    var count = 0L
    var x = 0
    var y = 0
    forEachLine {
        var line = it
        while (x >= line.length) line = "$line$it"
        if (y % yStep == 0) {
            val c = line[x]
            if (c == '#') count++
            x += xStep
        }
        y++
    }
    return count;
}

File("input/2020/day3").apply {
    var count = slope(1, 2)
    for(x in 1..7 step 2) count = count * slope(x, 1)
    println(count)
}
