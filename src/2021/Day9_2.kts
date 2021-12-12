import java.io.File
import kotlin.math.exp


fun ArrayList<Int>.fillString(): String {
    val filled = this.map {
        if (it < 10) "0$it" else "$it"
    }
    return filled.toString()
}

val floor = ArrayList<List<Int>>()
File("input/2021/day9").forEachLine { line ->
    floor.add(line.toList().map { it.digitToInt() })
}

val yMax = floor.size-1
val basinMap = ArrayList<ArrayList<Int>>()
var basinCount = 1
floor.forEachIndexed {y, row ->
    val basinRow = ArrayList<Int>()
    row.forEachIndexed { x, v ->
        if (v == 9) {
            basinCount++
            basinRow.add(0)
        }
        else basinRow.add(basinCount)
    }
    basinCount++
    basinMap.add(basinRow)
}

println()
basinMap.forEach {
    println("${it.fillString()}")
}

fun ArrayList<ArrayList<Int>>.neighbours(x: Int, y: Int): ArrayList<Int> {
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

fun join() {
    for (y in 1..basinMap.size-1) {
        val row = basinMap[y]
        val above = basinMap[y-1]
        for (x in 0..row.size-1) {
            if (above[x] > 0 && row[x] > 0) {
                val current = above[x]
                for (i in 0..row.size-1) {
                    if (above[i] == current) above[i] = row[x]
                }

                /*
                var s = x
                while (above[s] != 0 && s > 0) {
                    above[--s] = row[x]
                }
                s = x
                while (above[s] != 0 && s < above.size-1) {
                    above[++s] = row[x]
                }
                 */
            }
            println()
            basinMap.forEach {
                println("${it.fillString()}")
            }
        }
    }
}

join()

println()
basinMap.forEach {
    println("${it.fillString()}")
}
val basinSizes = basinMap.flatMap { it }
    .filter { it != 0 }
    .groupBy { it }
    .mapValues { it.value.size }
    .map { it.value }
    .sorted()
val (a,b,c) = basinSizes.subList(basinSizes.size-3, basinSizes.size)
println(a*b*c)

