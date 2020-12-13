import java.io.File

var parts = ArrayList<String>()
File("input/2020/day13").forEachLine { parts.add(it) }
val departureTime = parts[0].toLong()
var next = Long.MAX_VALUE
var id = 0L
parts[1].split(",").forEach {
    id = when (it) {
        "x" -> id
        else -> {
            var closest = it.toLong() * (departureTime / it.toLong())
            while (closest < departureTime) { closest += it.toLong() }
            if (closest < next) {
                next = closest
                it.toLong()
            } else id
        }
    }
}
println(id*(next-departureTime))