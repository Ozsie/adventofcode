import java.io.File

class Octopus(var energy: Int, var flashed: Boolean) {
    override fun toString(): String = "$energy"
}
fun updateNeighbour(y: Int, x: Int, grid: ArrayList<ArrayList<Octopus>>): Octopus? {
    if (y < grid.size && y >= 0 && x < grid[y].size && x >= 0) {
        if (!grid[y][x].flashed) {
            grid[y][x].energy++
            return grid[y][x]
        }
    }
    return null
}
fun Octopus.flash() {
    energy = 0
    flashed = true
}
fun Octopus.checkNeighbours(y: Int, x: Int): Int {
    var flashes = 0
    if (flashed) {
        var n = updateNeighbour(y-1, x, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y-1, x)
        }
        n = updateNeighbour(y-1, x-1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y-1, x-1)
        }
        n = updateNeighbour(y-1, x+1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y-1, x+1)
        }
        n = updateNeighbour(y, x-1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y, x-1)
        }
        n = updateNeighbour(y, x+1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y, x+1)
        }
        n = updateNeighbour(y+1, x, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y+1, x)
        }
        n = updateNeighbour(y+1, x-1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y+1, x-1)
        }
        n = updateNeighbour(y+1, x+1, grid)
        if (n != null && n.energy > 9 && !n.flashed) {
            n.flash()
            flashes++
            flashes += n.checkNeighbours(y+1, x+1)
        }
    }
    return flashes
}
val grid = ArrayList<ArrayList<Octopus>>()

File("input/2021/day11").forEachLine { line ->
    val row = ArrayList<Octopus>()
    val values = line.map { Octopus(it.digitToInt(), false) }
    row.addAll(values)
    grid.add(row)
}

var flashes = 0
for (step in 1..100) {
    grid.forEach { row ->
        row.forEach { octopus ->
            octopus.energy++
            octopus.flashed = false
        }
    }
    grid.forEachIndexed { y, row ->
        row.forEachIndexed { x, octopus ->
            if (octopus.energy > 9 && !octopus.flashed) {
                flashes++
                octopus.energy = 0
                octopus.flashed = true
                flashes += octopus.checkNeighbours(y,x)
            }
        }
    }
}
println(flashes)