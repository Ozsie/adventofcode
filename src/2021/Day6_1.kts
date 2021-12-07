import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

class Fish(var day: Int = 8) {
    override fun toString() = day.toString()
    fun passDay(): Fish? = if (day > 0) {
        day--
        null
    } else {
        day = 6
        Fish()
    }
}

val fishList = ArrayList<Fish>()
File("input/2021/day6").forEachLine { line ->
    fishList.addAll(line.split(",").map { Fish(it.toInt()) })
}

for (day in 1..80) {
    val newFish = fishList.map { it.passDay() }.filterNotNull()
    fishList.addAll(newFish)
}
println(fishList.size)