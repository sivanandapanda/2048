package org.example.core

internal class GameStatusChecker(
    private val winningTile: Int,
    private val gameBoard: GameBoard,
) {
    fun isGameOver(): Boolean {
        if (gameBoard.getEmptyTiles().isNotEmpty()) {
            return false
        }
        return !canMerge()
    }

    fun hasWon(): Boolean = hasValue(winningTile)

    private fun canMerge(): Boolean {
        val (leftBoard, _) = gameBoard.mergeLeft()
        if (!gameBoard.isEqual(leftBoard)) return true

        val (rightBoard, _) = gameBoard.mergeRight()
        if (!gameBoard.isEqual(rightBoard)) return true

        val (upBoard, _) = gameBoard.mergeUp()
        if (!gameBoard.isEqual(upBoard)) return true

        val (downBoard, _) = gameBoard.mergeDown()
        return !gameBoard.isEqual(downBoard)
    }

    private fun hasValue(value: Int): Boolean = gameBoard.getBoard().any { row -> row.any { tile -> tile == value } }
}
