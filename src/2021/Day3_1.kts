import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

fun String.lengthOfFiltered() = Pair(filter { it == '0' }.length, filter { it == '1' }.length)

var columns = ArrayList<String>()
File("input/2021/day3").forEachLine { line ->
    line.forEachIndexed { i, c ->
        columns.getOrElse(i) { columns.add(""); "" }
            .also { columns.set(i, it + c) }
    }
}
var gamma = ""
var epsilon = ""
columns.forEach {
    val (zeroes, ones) = it.lengthOfFiltered()
    gamma += if (ones > zeroes) 1 else 0
    epsilon += if (zeroes > ones) 1 else 0
}
println(gamma.toInt(2) * epsilon.toInt(2))