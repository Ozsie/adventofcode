import java.io.File

val input = ArrayList<CharArray>()

File("input/2020/day11").forEachLine {
    input.add(it.toCharArray())
}

for (y in 0..input.size) {
    val row = input[y]
    println(row)
    for (x in 0..row.size) {
        val seat = row[x]
        if (seat == '.') continue
        val newState = newState(x,y,seat,input)
        row.set(x,newState)
    }
}

fun newState(x: Int, y: Int, seat: Char, input: ArrayList<CharArray>): Char {
    var occupied = 0
    println("Checking $x $y")
    for (aY in IntRange(y-1,y+1)) {
        for (aX in IntRange(x-1,x+1)) {
            if (aY == y && aX == x) continue
            if (aY < 0 || aY >= input.size - 1) continue
            if (aX < 0 || aX >= input[aY].size - 1) continue
            println("$aX $aY")
            if (input[aY][aX] == '#') occupied++
        }
    }
    return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 4 && seat == '#') 'L' else seat
}