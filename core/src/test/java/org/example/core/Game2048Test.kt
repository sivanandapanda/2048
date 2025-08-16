package org.example.core

import org.example.model.MoveDirection
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class Game2048Test {
    @Nested
    inner class Setup {
        @Test
        fun `should setup a new game with a 4x4 board with 2 random tiles generated`() {
            val game = Game2048(4)
            assertEquals(4, game.getBoard().size)
            assertEquals(4, game.getBoard()[0].size)
            val nonEmptyTiles = game.getBoard().flatten().filterNotNull()
            assertEquals(2, nonEmptyTiles.size)
        }

        @Test
        fun `should have initial score of zero`() {
            val game = Game2048(4)
            assertEquals(0, game.getScore())
        }
    }

    @Nested
    inner class GamePlaying {
        @ParameterizedTest(name = "Move -> {0}")
        @EnumSource(MoveDirection::class)
        fun `should move the board in direction`(moveDirection: MoveDirection) {
            val game = Game2048(4)
            val initialBoard = game.getBoard()

            game.move(moveDirection)
            val newBoard = game.getBoard()
            assertTrue(game.getBoard().isNotEmpty())
            assertFalse(initialBoard.contentEquals(newBoard))
        }
    }
}
