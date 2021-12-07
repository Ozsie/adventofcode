import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

infix fun Int.lessThan(other: Int) = if(this < other) this else other

val crabs = ArrayList<Int>()
var maxChange = Int.MIN_VALUE

File("input/2021/day7").forEachLine { line ->
    crabs.addAll(line.split(",").map {
        if (it.toInt() > maxChange) maxChange = it.toInt()
        it.toInt()
    })
}

var fuelUse = Int.MAX_VALUE
for (position in 0..maxChange) {
    fuelUse = fuelUse lessThan crabs.map {
        if (it > position) it - position else position - it
    }.sum()
}
println(fuelUse)