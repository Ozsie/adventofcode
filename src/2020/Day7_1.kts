import java.io.File
import kotlin.collections.ArrayList

data class Bag(val color: String, val contents: HashMap<Bag,Int> = HashMap<Bag,Int>()) {
    fun findBagOrNew(color: String): Bag {
        val x = contents.keys.filter { color == it.color }
        val i = x.indexOfFirst { color == it.color }
        return if (i >= 0) x[i] else Bag(color)
    }

    fun look(lookingFor: String, top: Bag, matches: HashSet<Bag>): Unit {
        if (color == "TOP") {
            contents.forEach { bag, count -> bag.look(lookingFor, bag, matches)}
        } else {
            contents.forEach { bag, count ->
                if (bag.color == lookingFor) matches.add(top)
                if (bag.contents.isNotEmpty()) bag.look(lookingFor, top, matches)
            }
        }
    }
}

val bags = Bag("TOP", HashMap<Bag,Int>())

File("input/2020/day7").forEachLine {
    val (color,inner) = it.split(" bags contain ")
    val bag = bags.findBagOrNew(color)
    bags.contents.put(bag, 1)

    inner.trim().split(",")
        .filter { it != "no other bags." }
        .forEach {
            val descriptors = it.trim().split(" ")
            val count = descriptors[0].trim().toInt()
            val color = descriptors.subList(1, descriptors.lastIndex).joinToString(" ").trim()
            var innerBag = bags.findBagOrNew(color)
            if (!bags.contents.keys.contains(innerBag)) bags.contents.put(innerBag, 1)
            bag.contents.put(innerBag, count)
        }
}

val lookingFor = "shiny gold"
val matches = HashSet<Bag>()
bags.look(lookingFor,bags,matches)
println(matches.size)