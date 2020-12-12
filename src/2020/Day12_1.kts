import java.io.File

val faceOrder = listOf<String>("E","S","W","N")
var x = 0L
var y = 0L
var face = 0
File("input/2020/day12").forEachLine {
    when(it.substring(0,1)) {
        "N" -> y+= it.substring(1).toLong()
        "S" -> y-= it.substring(1).toLong()
        "E" -> x+= it.substring(1).toLong()
        "W" -> x-= it.substring(1).toLong()
        "R" -> face = turn(it.substring(0,1), it.substring(1).toInt())
        "L" -> face = turn(it.substring(0,1), it.substring(1).toInt())
        "F" -> when (face) {
            3 -> y+= it.substring(1).toLong()
            1 -> y-= it.substring(1).toLong()
            0 -> x+= it.substring(1).toLong()
            2 -> x-= it.substring(1).toLong()
        }
    }
}
println(Math.abs(x)+Math.abs(y))

fun turn(direction: String, degrees: Int): Int {
    var steps = degrees/90
    var new = face
    while (steps > 0) {
        when (direction) {
            "R" -> new = if (new == 3) 0 else new + 1
            "L" -> new = if (new == 0) 3 else new - 1
            else -> new
        }
        steps--
    }
    return new
}