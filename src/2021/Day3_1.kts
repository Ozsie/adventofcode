import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

var columns = HashMap<Int, ArrayList<Int>>()
File("input/2021/day3").forEachLine { line ->
    line.forEachIndexed { i, c ->
        val list = columns.getOrDefault(i, ArrayList<Int>())
        list.add(c.digitToInt())
        columns.put(i, list)
    }
}
var gamma = ""
var epsilon = ""
columns.map {
    val zeroes = it.value.filter { c -> c == 0 }.size
    val ones = it.value.filter { c -> c == 1 }.size
    gamma += if (zeroes > ones) "0" else if (ones > zeroes) "1" else "X"
    epsilon += if (zeroes > ones) "1" else if (ones > zeroes) "0" else "X"
}

println("${gamma.toInt(2)*epsilon.toInt(2)}")