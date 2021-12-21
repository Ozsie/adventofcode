import java.io.File

class DepthMap(val input: List<String>) {
    fun findBasins(): Collection<List<Pair<Int, Int>>> {
        val coordsWithBasinLabels = mutableMapOf<Pair<Int, Int>, Int>()
        var label = 0
        val coords = input.indices.product(input[0].indices)
        val depths = coords.associateWith { (i, j) -> input[i][j] }.withDefault { '@' }
        coords.filter { point ->
            depths.getValue(point) < point.neighbors().minOf { depths.getValue(it) }
        }
        coords.forEach { point -> searchBasin(point, coordsWithBasinLabels, label++, depths) }
        return coordsWithBasinLabels.entries.groupBy({ it.value }) { it.key }.values
    }

    private fun searchBasin(point: Pair<Int, Int>, coordsWithBasinLabels: MutableMap<Pair<Int,Int>, Int>, label: Int, depths: Map<Pair<Int, Int>, Char>) {
        if (point !in coordsWithBasinLabels && depths.getValue(point) < '9') {
            coordsWithBasinLabels[point] = label
            point.neighbors().forEach { searchBasin(it, coordsWithBasinLabels, label, depths) }
        }
    }
}

fun IntRange.product(other: IntRange) = this.flatMap { i -> other.map {
        j -> i to j
}}

fun Pair<Int, Int>.neighbors() = listOf(
    this.first - 1 to this.second,
    this.first + 1 to this.second,
    this.first     to this.second - 1,
    this.first     to this.second + 1,
)

val result = DepthMap(File("input/2021/day9")
    .readLines())
    .findBasins().map { it.size }.sortedBy { it }.takeLast(3).reduce { a, b -> a * b }
println(result)