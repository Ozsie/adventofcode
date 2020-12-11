import java.io.File

val input = ArrayList<CharArray>()

File("input/2020/day11").forEachLine { input.add(it.toCharArray()) }

var previous = input
var modified = copy(input)
var runs = 0
while (isChanged(previous,modified) || (runs++) == 0) {
  previous = copy(modified)
  modified = ArrayList<CharArray>()
  for (y in IntRange(0,input.size-1)) {
    modified.add(CharArray(previous[y].size))
    for (x in IntRange(0,previous[y].size-1)) {
      val newState = if (modified[y][x] == '.') '.' else newState(x,y,previous[y][x],previous)
      modified[y].set(x,newState)
    }
  }
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
  var d = lookVert(x,y,input,IntRange(y+1,input.lastIndex))
  var u = lookVert(x,y,input,y-1 downTo 0)
  var dr = lookDown(x,y,input,IntRange(x+1,input[y].lastIndex))
  var ur = lookUp(x,y,input,IntRange(x+1,input[y].lastIndex))
  var dl = lookDown(x,y,input,x-1 downTo 0)
  var ul = lookUp(x,y,input,x-1 downTo 0)
  var r = lookHori(x,y,input,IntRange(x + 1, input[y].lastIndex))
  var l = lookHori(x,y,input,x - 1 downTo 0)

  var occupied = u + ur + ul + d + dr + dl + r + l

  return if (occupied == 0 && seat == 'L') '#' else if (occupied >= 5 && seat == '#') 'L' else seat
}

fun lookUp(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  var aY = y-1
  for (aX in range) {
    if (aY < 0) break
    if (aY != y && aX != x && input[aY][aX] != '.' && hit == 0) {
      if (input[aY][aX] == '#') hit = 1 else break
    }
    aY--
  }
  return hit;
}

fun lookDown(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  var aY = y+1
  for (aX in range) {
    if (aY == input.size) break
    if (aY != y && aX != x && input[aY][aX] != '.' && hit == 0) {
      if (input[aY][aX] == '#') hit = 1 else break
    }
    aY++
  }
  return hit;
}

fun lookVert(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  for (aY in range) {
    if (aY != y && input[aY][x] != '.' && hit == 0) {
      if (input[aY][x] == '#') hit = 1 else break
    }
  }
  return hit
}

fun lookHori(x: Int, y: Int, input: ArrayList<CharArray>, range: IntProgression): Int {
  var hit = 0
  for (aX in range) {
    if (aX != x && input[y][aX] != '.' && hit == 0) {
      if (input[y][aX] == '#') hit = 1 else break
    }
  }
  return hit
}