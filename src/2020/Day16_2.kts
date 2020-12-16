/**
 * Now that you've identified which tickets contain invalid values, discard those tickets
 * entirely. Use the remaining valid tickets to determine which field is which.
 *
 * Using the valid ranges for each field, determine what order the fields appear on the tickets.
 * The order is consistent between all tickets: if seat is the third field, it is the third
 * field on every ticket, including your ticket.
 *
 * For example, suppose you have the following notes:
 *
 * ```
 * class: 0-1 or 4-19
 * row: 0-5 or 8-19
 * seat: 0-13 or 16-19
 *
 * your ticket:
 * 11,12,13
 *
 * nearby tickets:
 * 3,9,18
 * 15,1,5
 * 5,14,9
 * ```
 *
 * Based on the nearby tickets in the above example, the first position must be row, the second
 * position must be class, and the third position must be seat; you can conclude that in your
 * ticket, class is 12, row is 11, and seat is 13.
 *
 * Once you work out which field is which, look for the six fields on your ticket that start
 * with the word departure. What do you get if you multiply those six values together?
 */
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

        val notSetRules = rules.filter { rule ->
            candidates.filter { it.contains(rule) }.size == valid.size
        }
        if (notSetRules.size == 1) {
            notSetRules[0].order = i
            println(" Matched Rule @ $i for ${notSetRules[0]}")
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