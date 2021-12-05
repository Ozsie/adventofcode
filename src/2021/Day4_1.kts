import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

class Box(val value: Int, val x: Int, val y: Int, var selected: Boolean = false)

class Board(val boxes: ArrayList<Box>) {
    fun selectIf(number: Int) = boxes.filter { it.value == number }.forEach { it.selected = true }
    fun bingo(): Boolean {
        for (rowOrColumn in 0..5) {
            if (boxes.filter { it.x == rowOrColumn }.filter { it.selected }.size == 5 ||
                boxes.filter { it.y == rowOrColumn }.filter { it.selected }.size == 5) {
                return true
            }
        }
        return false
    }
    fun won(number: Int) = println(boxes.filter { !it.selected }.map { it.value }.sum() * number)
}

var numbers = ArrayList<Int>()
var boards = ArrayList<Board>()
var currentBoardY = 0

File("input/2021/day4").forEachLine { line ->
    if (numbers.isEmpty()) {
        numbers.addAll(line.split(",").map { it.toInt() })
    } else if (line.isBlank()) {
        currentBoardY = 0
        var currentBoard = Board(ArrayList<Box>())
        boards.add(currentBoard)
    } else {
        var row = line.split(" ")
            .filter { it.isNotBlank() }
            .mapIndexed { index, s ->  Box(s.trim().toInt(), index, currentBoardY) }
        boards.last().boxes.addAll(row)
        currentBoardY++
    }
}

var won = false
for (number in numbers) {
    for (board in boards) {
        board.selectIf(number)
        if (board.bingo() && !won) {
            won = true
            board.won(number)
        }
    }
    if (winner != null) break
}
