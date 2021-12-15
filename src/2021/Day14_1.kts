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

fun String.insert(rules: HashMap<String, String>): String {
    var newString = ""
    for (i in 0..length-2) {
        var pair = this.substring(i,i+2)
        val insertion = rules.getOrDefault(pair, "")
        newString = "$newString${pair[0]}$insertion"
    }
    return "$newString${this.last().toString()}"
}
var newString = start
for (step in 1..1) {
    newString = newString.insert(rules)
}
println(newString.toList().groupingBy { it }.eachCount())
val max = newString.toList().groupingBy { it }.eachCount().maxOf { it.value }
val min = newString.toList().groupingBy { it }.eachCount().minOf { it.value }
println(max-min)