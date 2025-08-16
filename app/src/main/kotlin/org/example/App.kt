package org.example

import org.example.core.Game2048
import org.example.model.MoveDirection
import org.example.ui.ConsoleGameRenderer
import java.util.Scanner

fun main() {
    val game = Game2048()
    val renderer = ConsoleGameRenderer(game)
    val scanner = Scanner(System.`in`)

    renderer.showWelcomeMessage()

    while (!game.isGameOver() && !game.hasWon()) {
        renderer.render()

        renderer.showMoveMessage()

        val input = scanner.nextLine()

        when (input?.trim()?.uppercase()) {
            "W" -> game.move(MoveDirection.UP)
            "A" -> game.move(MoveDirection.LEFT)
            "S" -> game.move(MoveDirection.DOWN)
            "D" -> game.move(MoveDirection.RIGHT)
            "Q" -> {
                renderer.showQuitMessage()
                return
            }

            else -> {
                renderer.showInvalidInputMessage()
                continue
            }
        }

        println()
    }

    renderer.render()

    if (game.hasWon()) {
        renderer.showWinningMessage()
    } else {
        renderer.showGameOverMessage()
    }

    renderer.showFinalScore()
}
