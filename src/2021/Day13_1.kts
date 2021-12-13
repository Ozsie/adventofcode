import java.io.File
var switched = false

val points = ArrayList<Pair<Int, Int>>()
val instructions = ArrayList<Pair<String, Int>>()
File("input/2021/day13").forEachLine { line ->
    if (line.isEmpty()) {
        switched = true
    } else {
        if (!switched) {
            val (x,y) = line.split(",").map { it.toInt() }
            points.add(Pair(x,y))
        } else {
            val inst = line.split(" ")
            val (dim,v) = inst[2].split("=")
            instructions.add(Pair(dim,v.toInt()))
        }
    }
}


val xMax = points.maxOf { it.first }
val yMax = points.maxOf { it.second }

var grid = ArrayList<ArrayList<String>>()
fun List<List<String>>.printGrid() = forEach {
    it.forEach { print(it) }
    println()
}
for (y in 0..yMax) {
    val row = ArrayList<String>()
    for (x in 0..xMax) {
        if (points.any { it.first == x && it.second == y }) {
            row.add("#")
        } else row.add(".")
    }
    grid.add(row)
}

fun fold(dim: String, value: Int) {
    if (dim == "y") {
        val top = grid.subList(0,value)
        val bottom = grid.subList(value+1,grid.size).reversed()
        val newGrid = top.mapIndexed { y, row ->
            val addRow = bottom[y]
            val mappedRow = row.mapIndexed { x, v ->
                if (addRow[x] == "#" || v == "#") "#" else "."
            }
            val newRow = ArrayList<String>()
            newRow.addAll(mappedRow)
            newRow
        }
        grid.clear()
        grid.addAll(newGrid)
    } else if (dim == "x") {
        val left = grid.map { row ->
            row.subList(0,value)
        }
        val right = grid.map { row ->
            row.subList(value+1,row.size).reversed()
        }
        val newGrid = left.mapIndexed { y, row ->
            val addRow = right[y]
            val mappedRow = row.mapIndexed { x, v ->
                if (addRow[x] == "#" || v == "#") "#" else "."
            }
            val newRow = ArrayList<String>()
            newRow.addAll(mappedRow)
            newRow
        }
        grid.clear()
        grid.addAll(newGrid)
    }
}

fold(instructions[0].first, instructions[0].second)
println(grid.flatMap { it }.filter { it == "#" }.size)