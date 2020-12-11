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
  var d = 0
  for (aY in IntRange(y+1,input.lastIndex)) {
    if (aY != y && input[aY][x] != '.' && d == 0) {
      if (input[aY][x] == '#') d = 1 else break
    }
  }

  var aY = y+1
  var dr = 0
  for (aX in IntRange(x+1,input[y].lastIndex)) {
    if (aY == input.size) break
    if (aY != y && aX != x && input[aY][aX] != '.' && dr == 0) {
      if (input[aY][aX] == '#') dr = 1 else break
    }
    aY++
  }

  aY = y+1
  var dl = 0
  for (aX in x-1 downTo 0) {
    if (aY == input.size) break
    if (aY != y && aX != x && input[aY][aX] != '.' && dl == 0) {
      if (input[aY][aX] == '#') dl = 1 else break
    }
    aY++
  }

  var u = 0
  for (aY in y-1 downTo 0) {
    if (aY != y && input[aY][x] != '.' && u == 0) {
      if (input[aY][x] == '#') u = 1 else break
    }
  }

  var ur = 0
  aY = y-1
  for (aX in IntRange(x+1,input[y].lastIndex)) {
    if (aY < 0) break
    if (aY != y && aX != x && input[aY][aX] != '.' && ur == 0) {
      if (input[aY][aX] == '#') ur = 1 else break
    }
    aY--
  }

  var ul = 0
  aY = y-1
  for (aX in x-1 downTo 0) {
    if (aY < 0) break
    if (aY != y && aX != x && input[aY][aX] != '.' && ul == 0) {
      if (input[aY][aX] == '#') ul = 1 else break
    }
    aY--
  }

  var r = 0
  for (aX in IntRange(x + 1, input[y].lastIndex)) {
    if (aX != x && input[y][aX] != '.' && r == 0) {
      if (input[y][aX] == '#') r = 1 else break
    }
  }
  var l = 0
  for (aX in x - 1 downTo 0) {
    if (aX != x && input[y][aX] != '.' && l == 0) {
      if (input[y][aX] == '#') l = 1 else break
    }
  }
  var occupied = u + ur + ul + d + dr + dl + r + l

  return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 5 && seat == '#') 'L' else seat
}
