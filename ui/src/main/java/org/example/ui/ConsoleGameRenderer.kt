package org.example.ui

import org.example.core.Game2048

class ConsoleGameRenderer : GameRenderer {
    override fun render(game: Game2048) {
        println("Score: ${game.getScore()}")
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
    }
}
