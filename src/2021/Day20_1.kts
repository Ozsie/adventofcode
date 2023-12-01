import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.max

fun ArrayList<ArrayList<Char>>.print() {
    forEach { row ->
        println(row.joinToString(""))
    }
}

fun ArrayList<ArrayList<Char>>.pad(padSize: Int): ArrayList<ArrayList<Char>> {
    val padded = ArrayList<ArrayList<Char>>()
    val newX = this[0].size+(padSize*2)
    val yPad = padSize/2
    for (i in 0..yPad) {
        val blankRow = ArrayList<Char>()
        for (x in 1..newX) blankRow.add('.')
        padded.add(blankRow)
    }
    forEach { row ->
        val paddedRow = ArrayList<Char>()
        for (x in 1..padSize) paddedRow.add('.')
        paddedRow.addAll(row)
        for (x in 1..padSize) paddedRow.add('.')
        padded.add(paddedRow)
    }
    for (i in 0..yPad) {
        val blankRow = ArrayList<Char>()
        for (x in 1..newX) blankRow.add('.')
        padded.add(blankRow)
    }
    return padded
}

var algorithm = ""
var image = ArrayList<ArrayList<Char>>()
File("input/2021/day20").forEachLine { line ->
    if (algorithm.isBlank()) algorithm = line
    else if (line.isNotBlank()) {
        image.add(ArrayList<Char>().apply { addAll(line.map { it }) })
    }
}
println(algorithm)
println()
image = image.pad(1)
var currentPadding = 1
for (enhancements in 0..1) {
    val enhancedImage = ArrayList<ArrayList<Char>>()
    image.print()
    image.forEachIndexed { y, row ->
        val newRow = ArrayList<Char>()
        row.forEachIndexed { x, pixel ->
            val topLeft = if (y == 0 || x == 0) '.' else image[y-1][x-1]
            val topMiddle = if (y == 0) '.' else image[y-1][x]
            val topRight = if (y == 0 || x == row.size-1) '.' else image[y-1][x+1]
            val left = if (x == 0) '.' else image[y][x-1]
            val right = if (x == row.size-1) '.' else image[y][x+1]
            val bottomLeft = if (y == image.size-1 || x == 0) '.' else image[y+1][x-1]
            val bottomMiddle = if (y == image.size-1) '.' else image[y+1][x]
            val bottomRight = if (y == image.size-1 || x == row.size-1) '.' else image[y+1][x+1]
            val enhanceFrom = "$topLeft$topMiddle$topRight$left${pixel}$right$bottomLeft$bottomMiddle$bottomRight"
            val index = enhanceFrom.map { if (it == '#') 1 else 0 }.joinToString("").toInt(2)

            val replacement = algorithm[index]
            newRow.add(replacement)

            if (x == 0 && y == 0)
              println("($x,$y) $enhanceFrom $index > $replacement")
        }
        enhancedImage.add(newRow)
    }
    println()
    image = enhancedImage.pad(1)
    currentPadding++
    println(enhancements)
}
image.print()
println(image.flatMap { it }.filter { it == '#' }.size)