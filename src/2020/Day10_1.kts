import java.io.File

val input = ArrayList<Long>()

File("input/2020/day10").forEachLine { input.add(it.toLong()) }
input.sort()
val chain = ArrayList<Long>()
var output = 0L
var oneDiff = 0
var threeDiff = 1

input.forEach { adapter ->
    if (adapter <= output + 3L) {
        chain.add(adapter)
        val diff = adapter - if (chain.indexOf(adapter) - 1 >= 0) chain[chain.indexOf(adapter) - 1] else 0L
        if (diff == 1L) oneDiff++ else if (diff == 3L) threeDiff++
        output += adapter
    }
}
println("${oneDiff*threeDiff}")