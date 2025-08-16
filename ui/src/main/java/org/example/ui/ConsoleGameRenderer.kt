package org.example.ui

import org.example.core.Game2048

class ConsoleGameRenderer(
    private val game: Game2048,
) : GameRenderer {
    override fun render() {
        println("Score: ${game.getScore()}")
        println("+" + "------+".repeat(4))

        for (row in game.getBoard()) {
            print("|")
            for (cell in row) {
                val displayValue = cell?.toString() ?: ""
                print("%5s|".format(displayValue))
            }
            println()
            println("+" + "------+".repeat(4))
        }
    }

    override fun showWinningMessage() {
        println("Congratulations! You reached 2048!")
    }

    override fun showGameOverMessage() {
        println("Game Over! No more moves possible.")
    }

    override fun showWelcomeMessage() {
        println("Welcome to 2048!")
        println("Use WASD keys to move: W=Up, A=Left, S=Down, D=Right, Q=Quit")
        println()
        print("Enter move (W/A/S/D/Q): ")
    }

    override fun showMoveMessage() {
        print("Enter move (W/A/S/D/Q): ")
    }

    override fun showQuitMessage() {
        println("Thanks for playing!")
    }

    override fun showInvalidInputMessage() {
        println("Invalid input! Use W/A/S/D to move or Q to quit.")
    }

    override fun showFinalScore() {
        println("Final Score: ${game.getScore()}")
    }
}
