import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2

data class Rule(val name: String, val lowerRange: IntRange, val upperRange: IntRange, var order: Int = -1)
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

val valid = nearby.filter { t ->
    t.filter { num ->
        rules.filter { it.matches(num) }.isEmpty()
    }.isEmpty()
}
var r = 1
while (rules.filter { it.order == -1 }.isNotEmpty()) {
    println("Round ${r++}")
    for (i in 0..rules.lastIndex) {
        val candidates = valid.map { t ->
            rules
                    .filter { it.order == -1 }
                    .filter { it.matches(t[i]) }
        }

        val notSetRules = rules.filter { it.order == -1 }.filter { rule ->
            candidates.filter { it.contains(rule) }.size == valid.size
        }
        if (notSetRules.size == 1) {
            println("Matched Rule @ $i for ${notSetRules[0]}")
            notSetRules[0].order = i
        }
    }
}

valid.forEach { t ->
    val valid = t.forEachIndexed { i,n ->
        var m = rules.filter { it.order == i }[0].matches(n)
        if (!m) println(t)
    }
}

val m = rules.filter { it.name.startsWith("departure") }.map {
    println(ticket[it.order])
    ticket[it.order]
}
if (m.isNotEmpty()) {
    val mul = m.reduce(Int::times)
    println(mul)
}