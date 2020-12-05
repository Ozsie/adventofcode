import java.io.File
import kotlin.collections.ArrayList
import kotlin.text.Regex
val fields = listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")
val eyes = listOf("amb","blu","brn","gry","grn","hzl","oth")
var passportList = ArrayList<String>()
var passport = ""
File("input/2020/day4").forEachLine {
    passport = if (!it.isEmpty()) "$passport $it" else {
        passportList.add(passport.trim())
        ""
    }
}
fun validateField(name: String, value: String) = when (name) {
    "byr" -> value.toInt() in 1920..2002
    "iyr" -> value.toInt() in 2010..2020
    "eyr" -> value.toInt() in 2020..2030
    "hgt" -> if (value.endsWith("cm")) {
        value.replace("cm", "").toInt() in 150..193
    } else if (value.endsWith("in")) {
        value.replace("in", "").toInt() in 59..76
    } else false
    "hcl" -> value.matches(Regex("#[\\da-f]{6}"))
    "ecl" -> eyes.contains(value)
    "pid" -> value.matches(Regex("[\\d]{9}"))
    else -> false
}

passportList.add(passport)
var count = 0
passportList.forEach { p ->
    if (fields.filter { p.contains(it) }.size == 7) {
        var validFields = 0
        p.split(" ").forEach { field ->
            if (field.split(":").size == 2) {
                val (name,value) = field.split(":")
                if (validateField(name, value)) validFields++
            }
        }
        if (validFields == 7) count++
    }
}
println(count)
