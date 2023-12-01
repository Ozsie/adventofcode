import java.io.File

val grid = ArrayList<ArrayList<Int>>()
File("input/2021/day15").forEachLine { line ->
    grid.add(ArrayList<Int>().apply { addAll(line.toList().map { it.digitToInt() }) })
}

fun ArrayList<Pair<Int, Pair<Int, Int>>>.addFor(y: Int, x: Int, from: ArrayList<ArrayList<Int>>) = add(Pair(from[y][x], Pair(y,x)))

fun ArrayList<ArrayList<Int>>.neighbours(y: Int, x: Int): ArrayList<Pair<Int, Pair<Int, Int>>> {
    val row = get(y)
    val nList = ArrayList<Pair<Int, Pair<Int, Int>>>()
    if (y == 0) {
        if (x == 0) {
            nList.addFor(y, x+1, this)
        } else if (x == row.size -1) {
            nList.addFor(y, x-1, this)
        } else {
            nList.addFor(y, x-1, this)
            nList.addFor(y, x+1, this)
        }
        nList.addFor(y+1, x, this)
    } else if (y == size-1) {
        if (x == row.size-1) {
            nList.addFor(y, x-1, this)
        } else if (x == 0) {
            nList.addFor(y, x+1, this)
        } else {
            nList.addFor(y, x-1, this)
            nList.addFor(y, x+1, this)
        }
        nList.addFor(y-1, x, this)
    } else {
        if (x > 0 && x < row.size-1) {
            nList.addFor(y, x-1, this)
            nList.addFor(y, x+1, this)
        } else if (x == 0) {
            nList.addFor(y, x+1, this)
        } else if (x == row.size-1) {
            nList.addFor(y, x-1, this)
        }
        nList.addFor(y+1, x, this)
        nList.addFor(y-1, x, this)
    }
    return nList
}

println()

var y = 0
var x = 0
var cameFrom = Pair(0,0)
var distance = 0

var steps = 0

var stack = ArrayList<Pair<Int, Pair<Int, Int>>>()

val distances = ArrayList<ArrayList<Int>>()
val originMap = ArrayList<ArrayList<Int>>()
val visited = ArrayList<ArrayList<Boolean>>()
val yMax = grid.size-1
val xMax = grid[0].size-1
for (y in 0..yMax) {
    val row = grid[y].map { 0 }
    distances.add(ArrayList<Int>().apply { addAll(row) })
    originMap.add(ArrayList<Int>().apply { addAll(row) })
    visited.add(ArrayList<Boolean>().apply { addAll(row.map { false }) })
}
grid.forEach { println(it) }
println()
distances.forEach { println(it) }



while (y != yMax && x != xMax && steps < 2) {
    if (x < xMax) {
        if (distances[y][x+1] > grid[y][x+1]+distances[y][x] && !visited[y][x+1]) {
            distances[y][x+1] = grid[y][x + 1] + distances[y][x]
            originMap[y][x+1] = np.ravel_multi_index([x, y], (max_val, max_val))
        }
    }
    # move to x-1,y
    if x>0:
    if distances[x-1,y]>grid[x-1,y]+distances[x,y] and not visited[x-1,y]:
    distances[x-1,y]=grid[x-1,y]+distances[x,y]
    originMap[x-1,y]=np.ravel_multi_index([x,y], (max_val,max_val))
    # move to x,y+1
    if y < max_val-1:
    if distances[x,y+1]>grid[x,y+1]+distances[x,y] and not visited[x,y+1]:
    distances[x,y+1]=grid[x,y+1]+distances[x,y]
    originMap[x,y+1]=np.ravel_multi_index([x,y], (max_val,max_val))
    # move to x,y-1
    if y>0:
    if distances[x,y-1]>grid[x,y-1]+distances[x,y] and not visited[x,y-1]:
    distances[x,y-1]=grid[x,y-1]+distances[x,y]
    originMap[x,y-1]=np.ravel_multi_index([x,y], (max_val,max_val))




    steps++
}