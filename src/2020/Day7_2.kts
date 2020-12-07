import java.io.File
import kotlin.collections.ArrayList

/*
shiny gold bag
  dark olive bag
    faded blue bags
    faded blue bags
    faded blue bags
    dotted black
    dotted black
    dotted black
    dotted black
  vibrant plum bag
    faded blue
    faded blue
    faded blue
    faded blue
    faded blue
    dotted black
    dotted black
    dotted black
    dotted black
    dotted black
    dotted black
  vibrant plum bag
    faded blue
    faded blue
    faded blue
    faded blue
    faded blue
    dotted black
    dotted black
    dotted black
    dotted black
    dotted black
    dotted black

 */

data class Bag(val color: String, val contents: ArrayList<Bag> = ArrayList<Bag>()) {
    fun findBagOrNew(color: String): Bag {
        val x = contents.filter { color == it.color }
        val i = x.indexOfFirst { color == it.color }
        return if (i >= 0) x[i] else Bag(color)
    }

    fun look(lookingFor: String): Long = contents.filter { it.color == lookingFor }.map { it.count() }.sum()

    fun count(): Long = contents.size + contents.map { c -> c.count() }.sum()
}

val bags = Bag("TOP", ArrayList<Bag>())

File("input/2020/day7").forEachLine {
    val (color,inner) = it.split(" bags contain ")
    val bag = bags.findBagOrNew(color)
    if (!bags.contents.contains(bag)) {
        bags.contents.add(bag)
    }

    inner.trim().split(",")
        .filter { it != "no other bags." }
        .forEach {
            val descriptors = it.trim().split(" ")
            val count = descriptors[0].trim().toInt()
            val color = descriptors.subList(1, descriptors.lastIndex).joinToString(" ").trim()
            var innerBag = bags.findBagOrNew(color)
            if (!bags.contents.contains(innerBag)) {
                bags.contents.add(innerBag)
            }
            for(i in 1..count) {
                bag.contents.add(innerBag)
            }
        }
}

val lookingFor = "shiny gold"
val matches = HashSet<Int>()
val total = bags.look(lookingFor)
println(total)