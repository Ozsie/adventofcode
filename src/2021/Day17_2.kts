import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.max

class Result(val initVel: Pair<Int,Int>, val maxY: Int, val hit: Boolean)

fun shoot(x: Int, y: Int, xRange: IntRange, yRange: IntRange): Result {
    var maxYPos = Int.MIN_VALUE
    var localYMax = Int.MIN_VALUE
    var position = Pair(0,0)
    var velocity = Pair(x,y)
    var steps = 0
    var over = false
    while (!(position.first in xRange && position.second in yRange)) {
        steps++
        //print("$steps: Vel: $velocity -> ")
        val nX = position.first + velocity.first
        val nY = position.second + velocity.second
        position = Pair(nX, nY)
        //print(" $position")
        if (nY > localYMax) localYMax = nY

        val inX = position.first >= xRange.first && position.first <= xRange.last
        val inY = position.second >= yRange.first && position.second <= yRange.last
        //print(" ($inX,$inY)")
        if (inX && inY) {
            //if (hitCount % 10 == 0)
            //println(" ($x,$y)")
            //else print(" ($x,$y)")
            break
        }

        val long = nX > Math.max(xRange.last, xRange.first)
        val toMuchY = nY < Math.min(yRange.last, yRange.first)
        //print(" ($long,$toMuchY)")
        if (long || toMuchY) {
            //println(" over at ($nX,$nY)")
            over = true
            break
        }

        val nVX = if (velocity.first > 0) velocity.first - 1 else if (velocity.first < 0) velocity.first + 1 else velocity.first
        val nVY = velocity.second - 1
        velocity = Pair(nVX, nVY)
        //println()
    }
    if (!over){
        //println(localYMax)
        maxYPos = max(localYMax, maxYPos)
    }
    return Result(Pair(x,y), maxYPos, !over)
}

File("input/2021/day17").forEachLine { line ->
    val (xTarget, yTarget) = line.substring(13).split(", ")
    val (xMin, xMax) = xTarget.substring(2).split("..").map { it.toInt() }.sortedBy { it }
    val (yMin, yMax) = yTarget.substring(2).split("..").map { it.toInt() }.sortedBy { it }
    val xRange = xMin.toInt()..xMax.toInt()
    val yRange = yMin.toInt()..yMax.toInt()
    //println(xRange)
    //println(yRange)

    var initVel = Pair(0,0)
    var maxYPos = Int.MIN_VALUE
    var hitCount = 0

    for (x in 0..700) {
        for (y in -500..500) {
            val result = shoot(x,y, xRange, yRange)
            hitCount += if (result.hit) 1 else 0
            if (result.hit) {
                maxYPos = max(result.maxY, maxYPos)
                initVel = result.initVel
            }
        }
    }

    println("$maxYPos, $initVel, $hitCount")
}