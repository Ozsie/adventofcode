import java.io.File
var count = 0
File("input/2020/day2").forEachLine {
    val (rule,value) = it.split(":").map { it.trim() }
    val (limits,letter) = rule.split(" ").map { it.trim() }
    val (low,high) = limits.split("-").map{ it.toInt() }
    if (value.filter { c -> c == letter[0] }.length in IntRange(low, high)) count++
}
println(count)
