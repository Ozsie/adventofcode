import java.io.File

val input = ArrayList<CharArray>()

File("input/2020/day11").forEachLine {
    input.add(it.toCharArray())
}

val yMax = input.size-1
for (y in 0..yMax) {
    val row = input[y]
    val xMax = row.size-1
    for (x in 0..xMax) {
        val seat = row[x]
        print(seat)
        if (seat == '.') continue
        val newState = newState(x,y,seat,input)
        row.set(x,newState)
    }
    print(" / ")
    print(row)
    println("")
}
println("-----------------")
for (y in 0..yMax) {
    val row = input[y]
    println(row)
}

fun newState(x: Int, y: Int, seat: Char, input: ArrayList<CharArray>): Char {
    var occupied = 0
    for (aY in IntRange(y-1,y+1)) {
        for (aX in IntRange(x-1,x+1)) {
            if (aY == y && aX == x) continue
            if (aY < 0) continue
            if (aY >= input.size) continue
            if (aX < 0) continue
            if (aX >= input[aY].size) continue
            if (input[aY][aX] == '#') {
                occupied++
            }
        }
    }
    return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 4 && seat == '#') 'L' else seat
}