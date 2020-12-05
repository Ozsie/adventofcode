import java.io.File
import kotlin.collections.ArrayList

val idList = ArrayList<Int>()
File("input/2020/day5").forEachLine { it ->

    fun ceil(input: Double): Int = Math.ceil(input).toInt()
    fun floor(input: Double): Int = Math.floor(input).toInt()

    fun find(range: IntRange, value: String, low: Char, high: Char): Int {
        var newRange: IntRange = IntRange(range.start, range.last)
        value.forEach { c ->
            newRange = when (c) {
                low -> {
                    val newEnd = floor((newRange.last - newRange.first).toDouble()/2)
                    IntRange(newRange.first, newRange.first + newEnd.toInt())
                }
                high -> {
                    val newStart = ceil((newRange.last - newRange.first).toDouble()/2.0)
                    IntRange(newRange.first + newStart.toInt(), newRange.last)
                }
                else -> newRange
            }
        }
        return if(value.last() == low) newRange.first else newRange.last
    }

    val row = it.substring(0, 7)
    val col = it.substring(7, 10)

    var rRange = 0..127
    var cRange = 0..7

    val id = find(cRange, col, 'L', 'R') + (8 * find(rRange, row, 'F', 'B'))
    idList.add(id)
}
idList.sort()
idList.forEach { id ->
    if (!idList.contains(id + 1) && idList.contains(id + 2)) println(id + 1)
}