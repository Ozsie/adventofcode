import java.io.File

val grid = mutableListOf<String>()
val symbols = Regex("[^\\.\\d\\s]")
val partNumbers = mutableListOf<Int>()

fun String.isPrevDigit(pos: Int) = pos > 0 && this[pos-1].isDigit()

File("input/2023/day3").apply {
    forEachLine { grid.add(it) }
    grid.forEachIndexed { y, row ->
        val numbers = row.split(Regex("\\D")).filter { it.isNotEmpty() }
        val passedIndices = mutableSetOf<Int>()
        numbers.forEach { number ->
            var xStart = row.indexOf(number)
            var offset: Int

            if (row.isPrevDigit(xStart)) passedIndices.add(xStart)

            while (passedIndices.contains(xStart)) {
                offset = xStart + number.length
                xStart = row.substring(xStart + number.length).indexOf(number) + offset
                if (row.isPrevDigit(xStart)) passedIndices.add(xStart)
            }
            passedIndices.add(xStart)
            val xEnd = xStart + number.length - 1
            var isPartNumber = false
            for (x in xStart..xEnd) {
                val lu = if (y > 0 && x > 0) symbols.matches(grid[y - 1][x - 1].toString()) else false
                val u = if (y > 0) symbols.matches(grid[y - 1][x].toString()) else false
                val ru = if (y > 0 && x < row.length - 1) symbols.matches(grid[y - 1][x + 1].toString()) else false

                val l = if (x - 1 < xStart && x > 0) symbols.matches(grid[y][x - 1].toString()) else false
                val r = if (x + 1 > xEnd && x < row.length - 1) symbols.matches(grid[y][x + 1].toString()) else false

                val ld = if (y < grid.size - 1 && x > 0) symbols.matches(grid[y + 1][x - 1].toString()) else false
                val d = if (y < grid.size - 1) symbols.matches(grid[y + 1][x].toString()) else false
                val rd = if (y < grid.size - 1 && x < row.length - 1) symbols.matches(grid[y + 1][x + 1].toString()) else false
                isPartNumber = lu || l || ld || u || d || ru || r || rd
                if (isPartNumber) break
            }
            if (isPartNumber) partNumbers.add(number.toInt())
        }
    }
}

println(partNumbers.sum())
