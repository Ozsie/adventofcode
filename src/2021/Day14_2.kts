import java.io.File

val rules = HashMap<String, String>()
var switched = false
var start = ""
File("input/2021/day14").forEachLine { line ->
    if (line.isEmpty()) {
        switched = true
    } else {
        if (!switched) {
            start = line
        } else {
            val (pair, insert) = line.split(" -> ")
            rules.put(pair, insert)
        }
    }
}

var pairs = HashMap<String, Int>()
for (i in 1..start.length-1) {
    val pair = "${start[i-1]}${start[i]}"
    val count = pairs.getOrDefault(pair, 0)
    pairs.put(pair, count+1)
}
println()
println(pairs)
for (step in 1..1) {
    val updated = HashMap<String, Int>()
    pairs.forEach { pair, count ->
        val insert = rules.get(pair)
        val pairA = "${pair[0]}$insert"
        val pairB = "$insert${pair[1]}"
        val countA = pairs.getOrDefault(pairA, 0)
        val countB = pairs.getOrDefault(pairB, 0)
        if (countA == 0) updated.put(pairA, count)
        else updated.put(pairA, countA+count)
        if (countB == 0) updated.put(pairB, count)
        else updated.put(pairB, countB+count)
    }
    pairs = updated
}
println(pairs.filter { it.key.contains('H') })

val letters = pairs.keys.joinToString("") { it }.toSortedSet()
println(letters)

val letterCount = HashMap<Char, Int>()
letters.forEach { c ->
    letterCount.put(c, pairs.filter { it.key.contains(c) }.map { it.value }.sum())
}
println(letterCount)

val max = letterCount.map { it.value }.maxOf { it }
val min = letterCount.map { it.value }.minOf { it }
println(max - min)
