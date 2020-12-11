import java.io.File

val input = ArrayList<CharArray>()

File("input/2020/day11").forEachLine { input.add(it.toCharArray()) }

var previous = input
var modified = copy(input)
var runs = 0
while (isChanged(previous,modified) || runs == 0) {
  previous = copy(modified)
  modified = ArrayList<CharArray>()
  for (y in IntRange(0,input.size-1)) {
      val row = previous[y]
      modified.add(CharArray(row.size))
      for (x in IntRange(0,row.size-1)) {
          val newState = if (modified[y][x] == '.') '.' else newState(x,y,previous[y][x],previous)
          modified[y].set(x,newState)
      }
  }
  runs++
}

var occupied = 0
modified.forEach { occupied += it.filter { it == '#' }.size }
println(occupied)

fun isChanged(previous: ArrayList<CharArray>, modified: ArrayList<CharArray>): Boolean {
    for (y in IntRange(0,previous.size-1)) {
        for (x in IntRange(0,previous[y].size-1)) {
            if (previous[y][x] != modified[y][x]) return true
        }
    }
    return false
}

fun copy(o: ArrayList<CharArray>) = ArrayList<CharArray>().apply {
      o.forEach {
          var newChArr = CharArray(it.size)
          it.copyInto(newChArr)
          add(newChArr)
      }
  }

fun newState(x: Int, y: Int, seat: Char, input: ArrayList<CharArray>): Char {
    var occupied = 0
    for (aY in IntRange(y-1,y+1)) {
        for (aX in IntRange(x-1,x+1)) {
            if (aY == y && aX == x) continue
            if (aY < 0 || aY >= input.size || aX < 0 || aX >= input[aY].size) continue
            if (input[aY][aX] == '#') occupied++
        }
    }
    return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 4 && seat == '#') 'L' else seat
}
