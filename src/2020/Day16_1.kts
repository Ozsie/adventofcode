import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2

data class Rule(val name: String, val lowerRange: IntRange, val upperRange: IntRange)
fun Rule.matches(num: Int) = num in lowerRange || num in upperRange
val f = File("input/2020/day16").readLines()
val rules = ArrayList<Rule>()
val ticket = ArrayList<Int>()
val nearby = ArrayList<List<Int>>()
var i = 0
while (i < f.size) {
    val line = f[i++]
    if (line.isEmpty()) continue

    when (line) {
        "your ticket:" -> {
            val x = f[i++]
            val t = x.split(",").map { it.toInt() }
            ticket.addAll(t)
        }
        "nearby tickets:" -> {
            while (i < f.size) {
                val t = f[i++].split(",").map { it.toInt() }
                nearby.add(t)
            }
        }
        else -> {
            val (name,ranges) = line.split(":")
            val (a,b) = ranges.trim().split(" or ")
            val (lA,hA) = a.split("-")
            val (lB,hB) = b.split("-")
            rules.add(Rule(name.trim(), (lA.toInt())..(hA.toInt()), (lB.toInt())..(hB.toInt())))
        }
    }
}

var sum = 0
nearby.forEach { t ->
    sum += t.filter { num ->
        val matchingRules = rules.filter {
            it.matches(num)
        }
        matchingRules.isEmpty()
    }.sum()
}
println(sum)