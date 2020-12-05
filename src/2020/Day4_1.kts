import java.io.File
import kotlin.collections.ArrayList
val fields = listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")
var passportList = ArrayList<String>()
var passport = ""
File("input/2020/day4").forEachLine {
    passport = if (!it.isEmpty()) "$passport $it" else {
        passportList.add(passport.trim())
        ""
    }
}
passportList.add(passport)
var count = 0
passportList.forEach { p ->
    if (fields.filter { p.contains(it) }.size == 7) count++
}
println(count)
