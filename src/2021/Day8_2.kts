import java.io.File

fun String.matchAnyOrder(values: String) = this.toList().containsAll(values.toList())
fun map(pattern: List<String>, nine: String, six: String, five: String, three: String, two: String, zero: String): Map<String, String> =
    mapOf<String, String>(
        Pair(pattern.filter { it.length == nine.length && it.matchAnyOrder(nine) }.first(), "9"),
        Pair(pattern.filter { it.length == 7 }.first(), "8"),
        Pair(pattern.filter { it.length == 3 }.first(), "7"),
        Pair(pattern.filter { it.length == six.length && it.matchAnyOrder(six) }.first(), "6"),
        Pair(pattern.filter { it.length == five.length && it.matchAnyOrder(five) }.first(), "5"),
        Pair(pattern.filter { it.length == 4 }.first(), "4"),
        Pair(pattern.filter { it.length == three.length && it.matchAnyOrder(three) }.first(), "3"),
        Pair(pattern.filter { it.length == two.length && it.matchAnyOrder(two) }.first(), "2"),
        Pair(pattern.filter { it.length == 2 }.first(), "1"),
        Pair(pattern.filter { it.length == zero.length && it.matchAnyOrder(zero) }.first(), "0")
    )

fun extrapolate(pattern: List<String>): Map<Int, String> {
    val four = pattern.filter { it.length == 4 }[0]
    val one = pattern.filter { it.length == 2 }[0]
    val three = pattern.filter { it.length == 5 && it.matchAnyOrder(one) }[0]
    val top = pattern.filter { it.length == 3 }
        .flatMap { it.toList() }
        .filter { !one.contains(it) }[0]
    val bottom = pattern.filter { it.length == 6 }
        .filter { it.matchAnyOrder(four) && it.contains(top) }
        .flatMap { it.toList() }
        .filter { !four.contains(it) && it != top }[0]
    val middle = three.filter { !one.contains(it) && it != top && it != bottom }[0]
    val topLeft = four.filter { !one.contains(it) && it != middle }[0]
    val five = pattern.filter { it.length == 5 && it.contains(topLeft) }[0]
    val topRight = one.filter { !five.contains(it) }[0]
    val bottomRight = one.filter { five.contains(it) }[0]
    val two = pattern.filter { it.length == 5 && it.contains(topRight) && !it.contains(bottomRight) }[0]
    val bottomLeft = two.filter { !three.contains(it) }[0]
    val nine = "$top$middle$bottom$topLeft$topRight$bottomRight"
    val six = "$top$middle$bottom$topLeft$bottomRight$bottomLeft"
    val zero = "$top$bottom$topLeft$topRight$bottomLeft$bottomRight"
    return mapOf<Int, String>(Pair(9,nine),Pair(6,six),Pair(5,five),Pair(3,three),Pair(2,two),Pair(0,zero))
}

val signals = ArrayList<Pair<String, String>>()
File("input/2021/day8").forEachLine { line ->
    val (pattern, output) = line.split("|").map { it.trim() }
    signals.add(Pair(pattern, output))
}
println(signals.map {
    val pattern = it.first.split(" ")
    val patterns: Map<Int, String> = extrapolate(pattern)
    val mapping = map(pattern, patterns[9]!!, patterns[6]!!, patterns[5]!!, patterns[3]!!, patterns[2]!!, patterns[0]!!)
    it.second.split(" ").map { digit ->
        mapping.filter { it.key.length == digit.length }.filter { it.key.matchAnyOrder(digit) }.map { it.value }[0]
    }.joinToString("").toLong()
}.sum())