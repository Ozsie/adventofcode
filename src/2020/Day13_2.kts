import java.io.File

var parts = ArrayList<String>()
File("input/2020/day13").forEachLine { parts.add(it) }

var busTimeOffsets = ArrayList<Pair<Long,Int>>()
parts[1].split(",").forEachIndexed { index, s -> if (s != "x") busTimeOffsets.add(Pair(s.toLong(),index)) }

var (increment,_) = busTimeOffsets[0]

var position = 0L
for (i in 1..busTimeOffsets.lastIndex) {
    val (busTime, busOffset) = busTimeOffsets[i]
    do { position += increment } while ((position + busOffset) % busTime != 0L)
    increment = increment * busTime
}
println(position)