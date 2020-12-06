import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
var count = 0
var answers = ""
var answerList = ArrayList<String>()
File("input/2020/day6").forEachLine {
    answers = if (!it.isEmpty()) "$answers$it" else {
        answerList.add(answers)
        ""
    }
}
answerList.add(answers)
answerList.forEach {
    var set = HashSet<Char>()
    it.forEach { c -> set.add(c) }
    count += set.size
}
println(count)