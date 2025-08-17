package org.example.core

internal class MoveHandler(
    private val gameBoard: GameBoard,
) {
    fun moveLeft(): Int {
        val (newBoard, moveScore) = gameBoard.mergeLeft()
        processMove(newBoard)
        return moveScore
    }

    fun moveRight(): Int {
        val (newBoard, moveScore) = gameBoard.mergeRight()
        processMove(newBoard)
        return moveScore
    }

    fun moveUp(): Int {
        val (newBoard, moveScore) = gameBoard.mergeUp()
        processMove(newBoard)
        return moveScore
    }

    fun moveDown(): Int {
        val (newBoard, moveScore) = gameBoard.mergeDown()
        processMove(newBoard)
        return moveScore
    }

    private fun processMove(newBoard: Array<Array<Int?>>) {
        if (gameBoard.isEqual(newBoard)) {
            return
        }

        gameBoard.updateBoard(newBoard)
    }
}
