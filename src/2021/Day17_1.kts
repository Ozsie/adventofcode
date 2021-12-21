import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.max

File("input/2021/day17").forEachLine { line ->
    val (xTarget, yTarget) = line.substring(13).split(", ")
    val (xMin, xMax) = xTarget.substring(2).split("..").map { it.toInt() }.sortedBy { it }
    val (yMin, yMax) = yTarget.substring(2).split("..").map { it.toInt() }.sortedBy { it }
    val xRange = xMin.toInt()..xMax.toInt()
    val yRange = yMin.toInt()..yMax.toInt()

    var initVel = Pair(0,0)
    var maxYPos = Int.MIN_VALUE
    for (x in 0..500) {
        for (y in -200..200) {
            var localYMax = Int.MIN_VALUE
            var position = Pair(0,0)
            var velocity = Pair(x,y)
            var steps = 0
            var over = false
            while (true) {
                steps++
                val nX = position.first + velocity.first
                val nY = position.second + velocity.second
                position = Pair(nX, nY)
                if (nY > localYMax) localYMax = nY

                val inX = position.first >= xRange.first && position.first <= xRange.last
                val inY = position.second >= yRange.first && position.second <= yRange.last
                if (inX && inY) break

                val short = (nX < xRange.first && nY < yRange.first)
                val long = (nX > xRange.last && nY < yRange.first)
                val toMuchY = (nY < yRange.last)
                if (short || long || toMuchY) {
                    over = true
                    break
                }

                val nVX = if (velocity.first > 0) velocity.first - 1 else if (velocity.first < 0) velocity.first + 1 else velocity.first
                val nVY = velocity.second - 1
                velocity = Pair(nVX, nVY)
            }
            if (!over){
                maxYPos = max(localYMax, maxYPos)
                initVel = Pair(x,y)
            }
        }
    }
    println(maxYPos)
}