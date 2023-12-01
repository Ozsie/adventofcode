import java.io.File

val numbers = mutableListOf<Int>()
val digitsAsStrings = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
)

fun String.containsAny(map: Map<String, String>) = map.keys.any {contains(it)}

File("input/2023/day1").apply {
    forEachLine { line ->
        var digits = ""
        line.forEachIndexed { i, c ->
            var subString = "$c"
            var nextIndex = i
            if (!c.isDigit()) {
                while (!digitsAsStrings.containsKey(subString) && nextIndex + 1 < line.length) {
                    nextIndex++
                    subString = "$subString${line[nextIndex]}"
                }
                if (digitsAsStrings.containsKey(subString)) digits = "$digits${digitsAsStrings[subString]}"
            } else digits = "$digits$subString"
        }
        numbers.add("${digits.first()}${digits.last()}".toInt())
    }
}
println(numbers.sumOf { it })
