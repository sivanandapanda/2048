package org.example.ui

import org.example.core.Game2048

interface GameRenderer {
    fun render(game: Game2048)

    fun showWinningMessage()

    fun showGameOverMessage()

    fun showWelcomeMessage()
}
