import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.collections.HashMap
var count = 0
var answers = ""
var answerList = ArrayList<String>()
fun ArrayList<String>.addIt(it: String) = add(it.substring(0, it.lastIndex))
File("input/2020/day6").forEachLine {
    answers = if (!it.isEmpty()) "$answers$it;" else {
        answerList.addIt(answers)
        ""
    }
}
answerList.addIt(answers)
answerList.forEach { group ->
    val persons = group.split(";").size
    var map = HashMap<Char, Int>()
    group.split(";").forEach { answers ->
        when (persons) {
            1 -> count+= answers.length
            else -> {
                answers.forEach {
                    map.put(it, map.getOrDefault(it, -1) + 1)
                }
            }
        }
    }
    println("$group ${map.filterValues { it > 0 }.filterValues { it+1==persons }}")
    count += map.filterValues { it > 0 }.filterValues { it+1==persons }.size
}
println(count)