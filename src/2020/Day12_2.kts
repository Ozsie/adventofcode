import java.io.File

var x = 10L
var y = 1L
var shipX = 0L
var shipY = 0L
File("input/2020/day12").forEachLine {
    val input = Pair(it.substring(0,1),it.substring(1).toLong())
    when(input.first) {
        "N" -> y+= input.second
        "S" -> y-= input.second
        "E" -> x+= input.second
        "W" -> x-= input.second
        in listOf("L","R") -> turn(x,y,input.first, input.second.toDouble()).also {
            x = it[0]
            y = it[1]
        }
        "F" -> move(x,y,input.second).also {
            shipX += it[0]
            shipY += it[1]
        }
    }
}
println(Math.abs(shipX)+Math.abs(shipY))

fun move(x: Long, y: Long, steps: Long) = listOf(x*steps,y*steps)

fun turn(x: Long, y: Long, direction: String, degrees: Double) = ArrayList<Long>().apply {
    val rad = degrees.toRadians(direction)
    val s = Math.round(Math.sin(rad))
    val c = Math.round(Math.cos(rad))
    addAll(listOf(((x*c)-(y*s)).toLong(),((x*s)+(y*c)).toLong()))
}

fun Double.toRadians(direction: String) = Math.toRadians(if (direction == "L") this else -this)