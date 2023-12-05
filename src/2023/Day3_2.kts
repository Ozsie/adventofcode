import java.io.File

val grid = mutableListOf<String>()
val symbols = Regex("[^\\.\\d\\s]")
val partNumbers = mutableListOf<List<Int>>()

fun String.isPrevDigit(pos: Int) = pos > 0 && this[pos-1].isDigit()

File("input/2023/day3").apply {
    forEachLine { grid.add(it) }
    val yMax = grid.size - 1
    val xMax = grid[0].length - 1
    for (y in IntRange(0, yMax)) {
        val row = grid[y]
        val starIndices = mutableListOf<Int>()
        if (!row.contains('*')) continue
        row.forEachIndexed { i, it -> if(it == '*') starIndices.add(i) }
        starIndices.forEach { x ->
            val adjacentNumbers = mutableListOf<Int>()
            val visitedIndices = mutableSetOf<String>()
            // Up Left
            if (y > 0 && x > 0 && grid[y-1][x-1].isDigit()) {
                var number = ""
                var steps = 1
                while (x-steps >= 0 && grid[y-1][x-steps].isDigit() && !visitedIndices.contains("${y-1},${x - steps}")) {
                    visitedIndices.add("${y-1},${x - steps}")
                    number = "${grid[y-1][x-steps]}$number"
                    steps++
                }
                steps = 0
                while (x+steps < row.length && grid[y-1][x+steps].isDigit() && !visitedIndices.contains("${y-1},${x+steps}")) {
                    visitedIndices.add("${y-1},${x+steps}")
                    number = "$number${grid[y-1][x+steps]}"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Up
            if (y > 0 && grid[y-1][x].isDigit()) {
                var number = ""
                var steps = 0
                while (x-steps >= 0 && grid[y-1][x-steps].isDigit() && !visitedIndices.contains("${y-1},${x+steps}")) {
                    visitedIndices.add("${y-1},${x+steps}")
                    number = "${grid[y-1][x-steps]}$number"
                    steps++
                }
                steps = 1
                while (x+steps < row.length && grid[y-1][x+steps].isDigit() && !visitedIndices.contains("${y-1},${x+steps}")) {
                    visitedIndices.add("${y-1},${x+steps}")
                    number = "$number${grid[y-1][x+steps]}"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Up Right
            if (y > 0 && x < xMax && grid[y-1][x+1].isDigit()) {
                var number = ""
                var steps = 1
                while (x+steps < row.length && grid[y-1][x+steps].isDigit() && !visitedIndices.contains("${y-1},${x+steps}")) {
                    visitedIndices.add("${y-1},${x+steps}")
                    number = "$number${grid[y-1][x+steps]}"
                    steps++
                }
                steps = 0
                while (x-steps >= 0 && grid[y-1][x-steps].isDigit() && !visitedIndices.contains("${y-1},${x+steps}")) {
                    visitedIndices.add("${y-1},${x+steps}")
                    number = "${grid[y-1][x-steps]}$number"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Left
            if (x > 0 && grid[y][x-1].isDigit()) {
                var number = ""
                var steps = 1
                while (x-steps >= 0 && grid[y][x-steps].isDigit() && !visitedIndices.contains("$y,${x-steps}")) {
                    visitedIndices.add("$y,${x-steps}")
                    number = "${grid[y][x-steps]}$number"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Right
            if (x < xMax && grid[y][x+1].isDigit()) {
                var number = ""
                var steps = 1
                while (x+steps < row.length && grid[y][x+steps].isDigit() && !visitedIndices.contains("$y,${x+steps}")) {
                    visitedIndices.add("$y,${x+steps}")
                    number = "$number${grid[y][x+steps]}"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Down Left
            if (y < yMax && x > 0 && grid[y+1][x-1].isDigit()) {
                var number = ""
                var steps = 1
                while (x-steps >= 0 && grid[y+1][x-steps].isDigit() && !visitedIndices.contains("${y+1},${x-steps}")) {
                    visitedIndices.add("${y+1},${x-steps}")
                    number = "${grid[y+1][x-steps]}$number"
                    steps++
                }
                steps = 0
                while (x+steps < row.length && grid[y+1][x+steps].isDigit() && !visitedIndices.contains("${y+1},${x+steps}")) {
                    visitedIndices.add("${y+1},${x+steps}")
                    number = "$number${grid[y+1][x+steps]}"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Down
            if (y < yMax && grid[y+1][x].isDigit()) {
                var number = ""
                var steps = 0
                while (x-steps >= 0 && grid[y+1][x-steps].isDigit() && !visitedIndices.contains("${y+1},${x-steps}")) {
                    visitedIndices.add("${y+1},${x-steps}")
                    number = "${grid[y+1][x-steps]}$number"
                    steps++
                }
                steps = 1
                while (x+steps < row.length && grid[y+1][x+steps].isDigit() && !visitedIndices.contains("${y+1},${x+steps}")) {
                    visitedIndices.add("${y+1},${x+steps}")
                    number = "$number${grid[y+1][x+steps]}"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            // Down Right
            if (y < yMax && x < xMax && grid[y+1][x+1].isDigit()) {
                var number = ""
                var steps = 1
                while (x+steps < row.length && grid[y+1][x+steps].isDigit() && !visitedIndices.contains("${y+1},${x+steps}")) {
                    visitedIndices.add("${y+1},${x+steps}")
                    number = "$number${grid[y+1][x+steps]}"
                    steps++
                }
                steps = 0
                while (x-steps >= 0 && grid[y+1][x-steps].isDigit() && !visitedIndices.contains("${y+1},${x-steps}")) {
                    visitedIndices.add("${y+1},${x-steps}")
                    number = "${grid[y+1][x-steps]}$number"
                    steps++
                }
                if (number.isNotEmpty()) adjacentNumbers.add(number.toInt())
            }
            if (adjacentNumbers.size == 2) partNumbers.add(adjacentNumbers)
        }
    }
}
val ratios = partNumbers.map { it.reduce { acc, i -> acc * i } }
println(ratios.sum()) // 72514855
