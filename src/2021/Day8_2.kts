import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

fun map(pattern: List<String>, nine: String, six: String, five: String, three: String, two: String, zero: String): Map<String, String> {
    return mapOf<String, String>(
        Pair(pattern.filter { it.length == nine.length }.filter { it.toList().containsAll(nine.toList()) }.first(), "9"),
        Pair(pattern.filter { it.length == 7 }.first(), "8"),
        Pair(pattern.filter { it.length == 3 }.first(), "7"),
        Pair(pattern.filter { it.length == six.length }.filter { it.toList().containsAll(six.toList()) }.first(), "6"),
        Pair(pattern.filter { it.length == five.length }.filter { it.toList().containsAll(five.toList()) }.first(), "5"),
        Pair(pattern.filter { it.length == 4 }.first(), "4"),
        Pair(pattern.filter { it.length == three.length }.filter { it.toList().containsAll(three.toList()) }.first(), "3"),
        Pair(pattern.filter { it.length == two.length }.filter { it.toList().containsAll(two.toList()) }.first(), "2"),
        Pair(pattern.filter { it.length == 2 }.first(), "1"),
        Pair(pattern.filter { it.length == zero.length }.filter { it.toList().containsAll(zero.toList()) }.first(), "0")
    )
}

fun extrapolate(pattern: List<String>): Map<Int, String> {
    val eight = pattern.filter { it.length == 7 }.first()
    val four = pattern.filter { it.length == 4 }.first()
    val one = pattern.filter { it.length == 2 }.first()

    val three = pattern.filter { it.length == 5 }.filter {
        it.toList().containsAll(one.toList())
    }.first()

    val top = pattern.filter { it.length == 3 }.flatMap { it.toList() }.filter { !one.contains(it) }.first()
    val bottom = pattern.filter { it.length == 6 }.filter {
        it.toList().containsAll(four.toList()) && it.contains(top)
    }.flatMap { it.toList() }.filter { !four.toList().contains(it) }.filter { it != top }.first()
    val middle = three.filter { !one.contains(it) }.filter { it != top }.filter { it != bottom }[0]

    val topLeft = four.filter { !one.contains(it) }.filter { it != middle }.first()

    val five = pattern.filter { it.length == 5 }.filter {
        it.toList().contains(topLeft)
    }.first()

    val topRight = one.filter { !five.contains(it) }.first()
    val bottomRight = one.filter { five.contains(it) }.first()

    val two = pattern.filter { it.length == 5 }.filter {
        it.toList().contains(topRight)
    }.filter { !it.toList().contains(bottomRight) }.first()

    val bottomLeft = two.filter { !three.toList().contains(it) }.first()
    val nine = "$top$middle$bottom$topLeft$topRight$bottomRight"
    val six = "$top$middle$bottom$topLeft$bottomRight$bottomLeft"
    val zero = "$top$bottom$topLeft$topRight$bottomLeft$bottomRight"
/*
    println(" $top$top$top$top ")
    println("$topLeft    $topRight")
    println("$topLeft    $topRight")
    println(" $middle$middle$middle$middle ")
    println("$bottomLeft    $bottomRight")
    println("$bottomLeft    $bottomRight")
    println(" $bottom$bottom$bottom$bottom ")
 */
    return mapOf<Int, String>(Pair(9,nine),Pair(6,six),Pair(5,five),Pair(3,three),Pair(2,two),Pair(0,zero))
}

val signals = ArrayList<Pair<String, String>>()

File("input/2021/day8").forEachLine { line ->
    val (pattern, output) = line.split("|").map { it.trim() }
    signals.add(Pair(pattern, output))
}
val numbers = signals.map {
    val pattern = it.first.split(" ")
    val patterns: Map<Int, String> = extrapolate(pattern)
    val mapping = map(pattern, patterns[9]!!, patterns[6]!!, patterns[5]!!, patterns[3]!!, patterns[2]!!, patterns[0]!!)
    it.second.split(" ").map { digit ->
        mapping.filter { it.key.length == digit.length }.filter { it.key.toList().containsAll(digit.toList()) }.map { it.value }.first()
    }.joinToString("").toLong()
}

println(numbers.sum())