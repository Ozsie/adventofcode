import java.io.File

var parts = ArrayList<String>()
File("input/2020/day13").forEachLine { parts.add(it) }
val departureTime = parts[0].toLong()
var next = Long.MAX_VALUE
var id = 0L
val buses = parts[1].split(",")
val max = buses.filter { it != "x" }.map { it.toLong() }.max() ?: 0L
val indexOfMax = buses.indexOf(max.toString())
println("$max at $indexOfMax")

var noMatch = true
var t = 0L-max
while (noMatch) {
    t += max
    if (t % 100000000000000L == 0L) println("At $t")
    var sequence = MutableList<Long>(buses.size, { i -> 1L })
    sequence.set(indexOfMax, 0L)
    for (x in (indexOfMax+1)..buses.lastIndex) {
        when(buses[x]) {
            "x" -> sequence.set(x, 0L)
            else -> {
                val mod = (t + (x - indexOfMax)) % buses[x].toLong()
                sequence.set(x, (t + (x - indexOfMax)) % buses[x].toLong())
                if (mod != 0L) break
            }
        }
    }
    if (sequence.sum() > 0L) continue;
    for (x in indexOfMax - 1 downTo 0) {
        when(buses[x]) {
            "x" -> sequence.set(x, 0L)
            else -> {
                val mod = (t - (indexOfMax - x)) % buses[x].toLong()
                sequence.set(x, mod)
                if (mod != 0L) break
            }
        }
    }
    noMatch = sequence.sum() != 0L
}
println(t)
