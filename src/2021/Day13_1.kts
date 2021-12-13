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
for (y in 0..yMax) {
    val row = ArrayList<String>()
    for (x in 0..xMax) {
        if (points.any { it.first == x && it.second == y }) {
            row.add("#")
        } else row.add(".")
    }
    grid.add(row)
}

fun List<List<String>>.merge(other: List<List<String>>) = mapIndexed { y, row ->
    val mappedRow = row.mapIndexed { x, v -> if (other[y][x] == "#" || v == "#") "#" else "." }
    ArrayList<String>().apply { addAll(mappedRow) }
}

fun ArrayList<ArrayList<String>>.fold(dim: String, value: Int): ArrayList<ArrayList<String>> {
    val newGrid = ArrayList<ArrayList<String>>()
    if (dim == "y") {
        val top = subList(0,value)
        val bottom = subList(value+1,grid.size).reversed()
        newGrid.addAll(top.merge(bottom))
    } else if (dim == "x") {
        val left = map { row -> row.subList(0,value) }
        val right = map { row -> row.subList(value+1,row.size).reversed() }
        newGrid.addAll(left.merge(right))
    }
    return newGrid
}

grid = grid.fold(instructions[0].first, instructions[0].second)
println(grid.flatMap { it }.filter { it == "#" }.size)