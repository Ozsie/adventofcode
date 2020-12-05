import java.io.File
var x = 0
var count = 0
File("input/2020/day3").forEachLine {
    var line = it
    while (x >= line.length) line = "$line$it"
    val c = line[x]
    if (c == '#') count++
    x+=3
}
println(count)
