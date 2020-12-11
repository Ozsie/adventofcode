import java.io.File

val input = ArrayList<CharArray>()

File("input/2020/day11").forEachLine { input.add(it.toCharArray()) }

var previous = input
var modified = copy(input)
var runs = 0
while (isChanged(previous,modified) || (runs++) == 0) {
  previous = copy(modified)
  modified = ArrayList<CharArray>()
  for (y in IntRange(0,input.lastIndex)) {
    modified.add(CharArray(previous[y].size))
    for (x in IntRange(0,previous[y].lastIndex)) {
      val newState = if (modified[y][x] == '.') '.' else newState(x,y,previous[y][x],previous)
      modified[y].set(x,newState)
    }
  }
}

var occupied = 0
modified.forEach { occupied += it.filter { it == '#' }.size }
println(occupied)

fun isChanged(previous: ArrayList<CharArray>, modified: ArrayList<CharArray>): Boolean {
  for (y in IntRange(0,previous.lastIndex)) {
    for (x in IntRange(0,previous[y].lastIndex)) { if (previous[y][x] != modified[y][x]) return true }
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
  var occupied = lookVert(x,y,input,IntRange(y+1,input.lastIndex)) +
          lookVert(x,y,input,y-1 downTo 0) +
          lookDiagDown(x,y,input,IntRange(x+1,input[y].lastIndex)) +
          lookDiagUp(x,y,input,IntRange(x+1,input[y].lastIndex)) +
          lookDiagDown(x,y,input,x-1 downTo 0) +
          lookDiagUp(x,y,input,x-1 downTo 0) +
          lookHori(x,y,input,IntRange(x + 1, input[y].lastIndex)) +
          lookHori(x,y,input,x - 1 downTo 0)
  return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 5 && seat == '#') 'L' else seat
}

fun lookDiagUp(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  var aY = y-1
  for (aX in range) {
    if (aY < 0) break
    if (input[aY][aX] != '.' && hit == 0) { if (input[aY][aX] == '#') hit = 1 else break }
    aY--
  }
  return hit;
}

fun lookDiagDown(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  var aY = y+1
  for (aX in range) {
    if (aY == input.size) break
    if (input[aY][aX] != '.' && hit == 0) { if (input[aY][aX] == '#') hit = 1 else break }
    aY++
  }
  return hit;
}

fun lookVert(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  for (aY in range) { if (input[aY][x] != '.' && hit == 0) { if (input[aY][x] == '#') hit = 1 else break } }
  return hit
}

fun lookHori(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  for (aX in range) { if (input[y][aX] != '.' && hit == 0) { if (input[y][aX] == '#') hit = 1 else break } }
  return hit
}
