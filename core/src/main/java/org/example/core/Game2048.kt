package org.example.core

import org.example.model.MoveDirection
import kotlin.random.Random

class Game2048(
    private val boardSize: Int = 4,
) {
    private val winningTile = 2048
    private val tileGenerator = TileGenerator()
    private val gameBoard = GameBoard(boardSize)
    private val moveHandler = MoveHandler(gameBoard)
    private val gameStatusChecker = GameStatusChecker(winningTile, gameBoard)
    private var score = 0

    init {
        repeat(2) {
            addRandomTile()
        }
    }

    fun getScore(): Int = score

    fun getBoard(): Array<Array<Int?>> = gameBoard.getBoard()

    fun move(direction: MoveDirection) {
        val moveScore =
            when (direction) {
                MoveDirection.LEFT -> moveHandler.moveLeft()
                MoveDirection.RIGHT -> moveHandler.moveRight()
                MoveDirection.UP -> moveHandler.moveUp()
                MoveDirection.DOWN -> moveHandler.moveDown()
            }
        score += moveScore
        addRandomTile()
    }

    private fun addRandomTile() {
        val emptyTiles = gameBoard.getEmptyTiles()
        if (emptyTiles.isNotEmpty()) {
            val (row, col) = emptyTiles[Random.nextInt(emptyTiles.size)]
            val randomTile = tileGenerator.generateTile()
            gameBoard.setTile(row, col, randomTile)
        }
    }

    fun hasWon(): Boolean = gameStatusChecker.hasWon()

    fun isGameOver(): Boolean = gameStatusChecker.isGameOver()
}
