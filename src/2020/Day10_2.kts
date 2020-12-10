import java.io.File

val input = ArrayList<Long>()

File("input/2020/day10").forEachLine { input.add(it.toLong()) }
input.apply {
    add(0)
    sort()
    add(last() + 3)
}
var pow7 = 0
var pow2 = 0
for (i in IntRange(1, input.size-2)) {
    val negative3: Long = if(i >= 3) input[i - 3] else -9999L
    if (input[i + 1] - negative3 == 4L) {
        pow7 += 1
        pow2 -= 2
    } else if (input[i + 1] - input[i - 1] == 2L) pow2 += 1
}
val ans = Math.pow(2.0,pow2.toDouble()) * Math.pow(7.0, pow7.toDouble())

println("${ans.toBigDecimal().toPlainString()}");