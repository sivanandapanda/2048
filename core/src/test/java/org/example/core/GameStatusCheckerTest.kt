package org.example.core

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameStatusCheckerTest {
    @Nested
    inner class GameOver {
        @Test
        fun `should return game over with full board and no moves`() {
            val board = GameBoard(4)
            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 4, 2),
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 4, 2),
                )
            board.updateBoard(initialBoard)

            val gameStatusChecker = GameStatusChecker(2048, board)

            assertTrue {
                gameStatusChecker.isGameOver()
            }
        }

        @Test
        fun `should return false for game over with full board but moves left`() {
            val board = GameBoard(4)
            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 4, 2),
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 4, 4),
                )
            board.updateBoard(initialBoard)

            val gameStatusChecker = GameStatusChecker(2048, board)

            assertFalse {
                gameStatusChecker.isGameOver()
            }
        }

        @Test
        fun `should return false for game over when board has empty tiles`() {
            val board = GameBoard(4)
            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 4, 2),
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf(4, 2, 4, null),
                )
            board.updateBoard(initialBoard)

            val gameStatusChecker = GameStatusChecker(2048, board)

            assertFalse {
                gameStatusChecker.isGameOver()
            }
        }
    }

    @Nested
    inner class GameWon {
        @Test
        fun `should return game won when tile 2048 is present`() {
            val board = GameBoard(4)
            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 2048, 2),
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf(4, 2, 4, null),
                )
            board.updateBoard(initialBoard)

            val gameStatusChecker = GameStatusChecker(2048, board)

            assertTrue {
                gameStatusChecker.hasWon()
            }
        }

        @Test
        fun `should return game not won when tile 2048 is not present`() {
            val board = GameBoard(4)
            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf<Int?>(4, 2, 1024, 2),
                    arrayOf<Int?>(2, 4, 2, 4),
                    arrayOf(4, 2, 4, null),
                )
            board.updateBoard(initialBoard)

            val gameStatusChecker = GameStatusChecker(2048, board)

            assertFalse {
                gameStatusChecker.hasWon()
            }
        }
    }
}
