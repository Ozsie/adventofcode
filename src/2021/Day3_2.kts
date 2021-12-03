import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

val rows = ArrayList<String>()
enum class Type { O, CO }

fun ArrayList<String>.reduce(column: Int, type: Type): ArrayList<String> {
    val zeroes = filter { it[column] == '0' }.size
    val ones = size - zeroes
    return when(type) {
        Type.O -> if (zeroes > ones) filter { it[column] == '0' } else filter { it[column] == '1' }
        Type.CO -> if (ones < zeroes) filter { it[column] == '1' } else filter { it[column] == '0' }
    } as ArrayList<String>
}

var oxygen = ArrayList<String>()
var co2 = ArrayList<String>()
File("input/2021/day3").forEachLine {
    oxygen.add(it)
    co2.add(it)
}
val cols = oxygen[0].length

for (col in 0..cols) {
    if (oxygen.size > 1) oxygen = oxygen.reduce(col, Type.O)
    if (co2.size > 1) co2 = co2.reduce(col, Type.CO)
}
println(oxygen[0].toInt(2) * co2[0].toInt(2))