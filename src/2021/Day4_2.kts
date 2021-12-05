import java.io.File
import kotlin.collections.HashMap
import kotlin.collections.ArrayList

class Box(val value: Int, val x: Int, val y: Int, var selected: Boolean = false)

class Board(val boxes: ArrayList<Box>, var order: Int = -1, var winningNumber: Int = 1) {
    public var competing = true
    fun selectIf(number: Int) = boxes.filter { it.value == number }.forEach { it.selected = true }
    fun bingo(): Boolean {
        for (rowOrColumn in 0..5) {
            if (boxes.filter { it.x == rowOrColumn }.filter { it.selected }.size == 5 ||
                boxes.filter { it.y == rowOrColumn }.filter { it.selected }.size == 5) {
                competing = false
                return true
            }
        }
        return false
    }
    fun won() = println(boxes.filter { !it.selected }.map { it.value }.sum() * winningNumber)
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
        var split = line.split(" ")
        var row = split.filter { it.isNotBlank() }.mapIndexed { index, s ->  Box(s.trim().toInt(), index, currentBoardY) }
        boards.last().boxes.addAll(row)
        currentBoardY++
    }
}

var winOrder = 1
for (number in numbers) {
    val competingBoards = boards.filter { it.competing }
    for (board in competingBoards) {
        board.selectIf(number)
        if (board.bingo()) {
            board.order = winOrder++
            board.winningNumber = number
        }
    }
}
boards.filter { it.order == winOrder - 1 }.first().won()
