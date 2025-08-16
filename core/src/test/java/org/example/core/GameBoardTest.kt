package org.example.core

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class GameBoardTest {

    @Test
    fun `should create a game board of given size`() {
        val size = 3
        val gameBoard = GameBoard(size)

        val expected: Array<Array<Int?>> = arrayOf(
            arrayOf(null, null, null),
            arrayOf(null, null, null),
            arrayOf(null, null, null)
        )

        assertArrayEquals(expected, gameBoard.getBoard())
    }
}