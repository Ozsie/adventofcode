import java.io.File
var count = 0
File("input/2020/day2").forEachLine {
    val (rule,value) = it.split(":").map { it.trim() }
    val (limits,letter) = rule.split(" ").map { it.trim() }
    val (x,y) = limits.split("-").map{ it.toInt() - 1 }
    if ((value[x] == letter[0]).xor(value[y] == letter[0])) count++
}
println(count)
