import java.io.File

fun ArrayList<List<Int>>.neighbours(x: Int, y: Int): ArrayList<Int> {
    val row = get(y)
    val nList = ArrayList<Int>()
    if (y == 0) {
        if (x == 0) {
            nList.add(this[y][x+1])
        } else if (x == row.size -1) {
            nList.add(this[y][x-1])
        } else {
            nList.add(this[y][x+1])
            nList.add(this[y][x-1])
        }
        nList.add(this[y+1][x])
    } else if (y == size-1) {
        if (x == row.size-1) {
            nList.add(this[y][x-1])
        } else if (x == 0) {
            nList.add(this[y][x+1])
        } else {
            nList.add(this[y][x+1])
            nList.add(this[y][x-1])
        }
        nList.add(this[y-1][x])
    } else {
        if (x > 0 && x < row.size-1) {
            nList.add(this[y][x+1])
            nList.add(this[y][x-1])
        } else if (x == 0) {
            nList.add(this[y][x+1])
        } else if (x == row.size-1) {
            nList.add(this[y][x-1])
        }
        nList.add(this[y+1][x])
        nList.add(this[y-1][x])
    }
    return nList
}

val floor = ArrayList<List<Int>>()
File("input/2021/day9").forEachLine { line ->
    floor.add(line.toList().map { it.digitToInt() })
}

var risk = 0
floor.forEachIndexed {y, row ->
    row.forEachIndexed { x, v ->
        val neighbors = floor.neighbours(x,y)
        val lowerN = neighbors.filter { it <= v }.size
        if (lowerN == 0) risk += v+1
    }
}
println(risk)