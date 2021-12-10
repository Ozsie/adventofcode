import java.io.File

fun ArrayList<List<Int>>.expandOn(y: Int, x: Int, points: ArrayList<String>) {
    val exists = basins.filter { it.points.contains("$y$x") }.size != 0
    if (!exists && get(y)[x] != 9) {
        points.add("$y$x")
        if (y < size-1) expandOn(y+1,x, points)
        if (x < get(y).size-1) expandOn(y,x+1, points)
    }
}
fun List<Basin>.merge(): List<Basin> {
    val toDelete = ArrayList<Basin>()
    for(i in 0..this.size-1) {
        val basin = get(i)
        val inner = i+1
        for (j in inner..this.size-1) {
            val next = get(j)
            if (basin.adjacentTo(next)) {
                basin.points.addAll(next.points)
                toDelete.add(next)
            }
        }
    }
    return this.filter { !toDelete.contains(it) };
}

fun Basin.adjacentTo(other: Basin): Boolean = this.points.any { one ->
    other.points.map { it.toList().map { it.digitToInt() } }.all { bList ->
        val aList = one.toList().map { it.digitToInt() }
        val y = Math.abs(aList[0]-bList[0])
        val x = Math.abs(aList[1]-bList[1])
        x <= 1 && y <= 1
    }
}

class Basin(val points: ArrayList<String>, var size: Int = 0) {
    override fun toString(): String {
        return points.toString()
    }
}
val basins = ArrayList<Basin>()
val floor = ArrayList<List<Int>>()
File("input/2021/day9").forEachLine { line ->
    floor.add(line.toList().map { it.digitToInt() })
}

val yMax = floor.size-1
floor.forEachIndexed {y, row ->
    row.forEachIndexed { x, v ->
        val basinPoints = ArrayList<String>()
        floor.expandOn(y, x, basinPoints)
        basins.add(Basin(basinPoints))
    }
}

val joined = basins.filter { !it.points.isEmpty() }.merge().sortedBy {
    it.points.size
}
println(joined)
val max = joined.subList(joined.size-3, joined.size)
println(max)
println(max.map { it.points.size })