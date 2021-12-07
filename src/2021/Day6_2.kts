import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

var fishMap = HashMap<Int, Long>()
File("input/2021/day6").forEachLine { line ->
    line.split(",").forEach {
        fishMap.put(it.toInt(), fishMap.getOrDefault(it.toInt(), 0L) + 1)
    }
}

for (day in 1..256) {
    var shifted = HashMap<Int, Long>()
    val spawnCount = fishMap.getOrDefault(0, 0L)
    for (days in 8 downTo 1) {
        val fishCount = fishMap.getOrDefault(days, 0L)
        shifted.put(days-1, fishCount)
    }
    shifted.put(8, shifted.getOrDefault(8,0) + spawnCount)
    shifted.put(6, shifted.getOrDefault(6,0) + spawnCount)
    fishMap = shifted
}
println(fishMap.values.sum())