package org.example.core

internal class GameBoard(
    private val size: Int,
) {
    private val board: Array<Array<Int?>> = Array(size) { Array(size) { null } }

    fun getBoard(): Array<Array<Int?>> =
        Array(size) { row ->
            Array(size) { col ->
                board[row][col]
            }
        }

    fun updateBoard(newBoard: Array<Array<Int?>>) {
        for (row in 0 until size) {
            for (col in 0 until size) {
                board[row][col] = newBoard[row][col]
            }
        }
    }

    fun setTile(
        row: Int,
        col: Int,
        value: Int,
    ) {
        board[row][col] = value
    }

    fun mergeLeft(): Pair<Array<Array<Int?>>, Int> {
        val newBoard: Array<Array<Int?>> = Array(size) { Array(size) { null } }
        var score = 0

        for (row in 0 until size) {
            val (mergeResult, mergeScore) = mergeRow(board[row].toList())
            newBoard[row] = mergeResult
            score += mergeScore
        }

        return Pair(newBoard, score)
    }

    fun mergeRight(): Pair<Array<Array<Int?>>, Int> {
        val newBoard: Array<Array<Int?>> = Array(size) { Array(size) { null } }
        var score = 0

        for (row in 0 until size) {
            val (mergeResult, mergeScore) = mergeRow(board[row].reversed())
            newBoard[row] = mergeResult.reversed().toTypedArray()
            score += mergeScore
        }

        return Pair(newBoard, score)
    }

    fun mergeUp(): Pair<Array<Array<Int?>>, Int> {
        val newBoard: Array<Array<Int?>> = Array(size) { Array(size) { null } }
        var score = 0

        for (col in 0 until size) {
            val column = (0 until size).map { board[it][col] }
            val (mergeResult, mergeScore) = mergeRow(column)
            for (row in 0 until size) {
                newBoard[row][col] = mergeResult[row]
            }
            score += mergeScore
        }

        return Pair(newBoard, score)
    }

    fun mergeDown(): Pair<Array<Array<Int?>>, Int> {
        val newBoard: Array<Array<Int?>> = Array(size) { Array(size) { null } }
        var score = 0

        for (col in 0 until size) {
            val column = (0 until size).map { board[it][col] }.reversed()
            val (mergeResult, mergeScore) = mergeRow(column)
            val reversedResult = mergeResult.reversed()
            for (row in 0 until size) {
                newBoard[row][col] = reversedResult[row]
            }
            score += mergeScore
        }

        return Pair(newBoard, score)
    }

    private fun mergeRow(row: List<Int?>): Pair<Array<Int?>, Int> {
        val newRow = mutableListOf<Int?>()
        val nonNullValues = row.filterNotNull()
        var score = 0
        var i = 0

        while (i < nonNullValues.size) {
            if (i < nonNullValues.size - 1 && nonNullValues[i] == nonNullValues[i + 1]) {
                val mergedValue = nonNullValues[i] * 2
                score += mergedValue
                newRow.add(mergedValue)
                i += 2
            } else {
                newRow.add(nonNullValues[i])
                i++
            }
        }

        while (newRow.size < size) {
            newRow.add(null)
        }

        return Pair(newRow.toTypedArray(), score)
    }

    fun getEmptyTiles(): List<Pair<Int, Int>> {
        val emptyTiles = mutableListOf<Pair<Int, Int>>()
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (board[row][col] == null) {
                    emptyTiles.add(Pair(row, col))
                }
            }
        }
        return emptyTiles
    }

    fun isEqual(other: Array<Array<Int?>>): Boolean {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (board[row][col] != other[row][col]) {
                    return false
                }
            }
        }
        return true
    }
}
