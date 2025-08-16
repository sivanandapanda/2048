package org.example.core

class GameBoard(
    private val size: Int,
) {
    private val board: Array<Array<Int?>> = Array(size) { Array(size) { null } }

    fun getBoard(): Array<Array<Int?>> {
        return Array(size) { row ->
            Array(size) { col ->
                board[row][col]
            }
        }
    }

}
