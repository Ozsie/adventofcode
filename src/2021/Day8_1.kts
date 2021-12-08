import java.io.File

val signals = ArrayList<Pair<String, String>>()
File("input/2021/day8").forEachLine { line ->
    val (pattern, output) = line.split("|").map { it.trim() }
    signals.add(Pair(pattern, output))
}
println(signals.map { it.second }.flatMap { it.split(" ") }.filter {
    when(it.length) {
        2 -> true
        3 -> true
        4 -> true
        7 -> true
        else -> false
    }
}.size)