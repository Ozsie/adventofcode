import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

class Point(val x: Int, val y: Int)
class Line(val start: Point, val end: Point) {
    fun extrapolatePoints(): ArrayList<Point> {
        val extrapolated = ArrayList<Point>()
        if (start.y == end.y) {
            if (start.x < end.x) for (x in start.x..end.x) {
                extrapolated.add(Point(x, start.y))
            } else for (x in end.x..start.x) {
                extrapolated.add(Point(x, start.y))
            }
        } else if (start.x == end.x) {
            if (start.y < end.y) for (y in start.y..end.y) {
                extrapolated.add(Point(start.x, y))
            } else for (y in end.y..start.y) {
                extrapolated.add(Point(start.x, y))
            }
        } else {
            if (start.x < end.x && start.y < end.y) for (p in 0..(end.y-start.y)) {
                extrapolated.add(Point(start.x+p, start.y+p))
            } else if (start.x < end.x && start.y > end.y) for (p in 0..(start.y-end.y)) {
                extrapolated.add(Point(start.x+p, start.y-p))
            } else if (start.x > end.x && start.y > end.y) for (p in 0..(start.x-end.x)) {
                extrapolated.add(Point(start.x-p, start.y-p))
            } else if (start.x > end.x && start.y < end.y)  for (p in 0..(start.x-end.x)) {
                extrapolated.add(Point(start.x-p, start.y+p))
            }
        }
        return extrapolated
    }
}

fun String.asPoint(): Point = Point(split(",")[0].toInt(),split(",")[1].toInt())

val lines = ArrayList<Line>()

File("input/2021/day5").forEachLine { line ->
    val (start, end) = line.split(" -> ").map { it.asPoint() }
    lines.add(Line(start, end))
}

val hits = HashMap<String, Int>()

lines.forEach {
    it.extrapolatePoints().forEach {
        var hitsAtPoint = hits.getOrElse(it.toString()) { 0 }
        hits.put(it.toString(), ++hitsAtPoint)
    }
}

println(hits.filterValues { it > 1 }.size)
