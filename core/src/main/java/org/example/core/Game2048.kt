package org.example.core

class Game2048(
    private val boardSize: Int = 4,
) {
    private val winningTile = 2048
    private val tileGenerator = TileGenerator()
    private val gameBoard = GameBoard(boardSize)

    private var score = 0

    fun getScore(): Int = score

    fun getBoard(): Array<Array<Int?>> = gameBoard.getBoard()
}
