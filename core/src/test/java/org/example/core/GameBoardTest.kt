package org.example.core

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameBoardTest {
    @Nested
    inner class Setup {
        @Test
        fun `should create a game board of given size`() {
            val size = 3
            val gameBoard = GameBoard(size)

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(null, null, null),
                    arrayOf(null, null, null),
                    arrayOf(null, null, null),
                )

            assertArrayEquals(expected, gameBoard.getBoard())
        }

        @Test
        fun `should set value to a tile`() {
            val size = 3
            val gameBoard = GameBoard(size)
            gameBoard.setTile(1, 1, 2)

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(null, null, null),
                    arrayOf(null, 2, null),
                    arrayOf(null, null, null),
                )

            assertArrayEquals(expected, gameBoard.getBoard())
        }

        @Test
        fun `should update game board with provided board`() {
            val gameBoard = GameBoard(4)

            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(null, 8, 2, 2),
                    arrayOf<Int?>(4, 2, null, 2),
                    arrayOf<Int?>(null, null, null, null),
                    arrayOf<Int?>(null, null, null, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(null, 8, 2, 2),
                    arrayOf(4, 2, null, 2),
                    arrayOf(null, null, null, null),
                    arrayOf(null, null, null, 2),
                )

            assertArrayEquals(expected, gameBoard.getBoard())
        }
    }

    @Nested
    inner class MergeLeft {
        @Test
        fun `should merge left when tiles can be merged`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf(null, 4, 2),
                    arrayOf(2, null, 2),
                    arrayOf<Int?>(2, 2, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeLeft()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(4, 2, null),
                    arrayOf(4, null, null),
                    arrayOf(4, 2, null),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(8, score)
        }

        @Test
        fun `should not do anything when no empty tile`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(2, 8, 2),
                    arrayOf<Int?>(2, 4, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeLeft()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(2, 8, 2),
                    arrayOf<Int?>(2, 4, 2),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(0, score)
        }
    }

    @Nested
    inner class MergeRight {
        @Test
        fun `should merge right when tiles can be merged`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf(null, 4, 2),
                    arrayOf(2, null, 2),
                    arrayOf<Int?>(2, 2, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeRight()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(null, 4, 2),
                    arrayOf(null, null, 4),
                    arrayOf(null, 2, 4),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(8, score)
        }

        @Test
        fun `should not do anything when no empty tile`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(2, 8, 2),
                    arrayOf<Int?>(2, 4, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeRight()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(2, 8, 2),
                    arrayOf<Int?>(2, 4, 2),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(0, score)
        }
    }

    @Nested
    inner class MergeUp {
        @Test
        fun `should merge up when tiles can be merged`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf(null, 4, 2),
                    arrayOf(2, null, 2),
                    arrayOf<Int?>(2, 2, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeUp()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(4, 4, 4),
                    arrayOf(null, 2, 2),
                    arrayOf(null, null, null),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(8, score)
        }

        @Test
        fun `should not do anything when no empty tile`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(4, 2, 4),
                    arrayOf<Int?>(2, 4, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeUp()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(4, 2, 4),
                    arrayOf<Int?>(2, 4, 2),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(0, score)
        }
    }

    @Nested
    inner class MergeDown {
        @Test
        fun `should merge down when tiles can be merged`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf(null, 4, 2),
                    arrayOf(2, null, 2),
                    arrayOf<Int?>(2, 2, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeDown()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf(null, null, null),
                    arrayOf(null, 4, 2),
                    arrayOf(4, 2, 4),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(8, score)
        }

        @Test
        fun `should not do anything when no empty tile`() {
            val gameBoard = GameBoard(3)

            val initialBoard =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(8, 8, 4),
                    arrayOf<Int?>(2, 4, 2),
                )
            gameBoard.updateBoard(initialBoard)

            val (mergedBoard, score) = gameBoard.mergeDown()

            val expected: Array<Array<Int?>> =
                arrayOf(
                    arrayOf<Int?>(16, 4, 2),
                    arrayOf<Int?>(8, 8, 4),
                    arrayOf<Int?>(2, 4, 2),
                )

            assertArrayEquals(expected, mergedBoard)
            assertEquals(0, score)
        }
    }
}
