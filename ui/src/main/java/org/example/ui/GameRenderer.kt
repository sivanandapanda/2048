package org.example.ui

interface GameRenderer {
    fun render()

    fun showWinningMessage()

    fun showGameOverMessage()

    fun showWelcomeMessage()

    fun showMoveMessage()

    fun showQuitMessage()

    fun showInvalidInputMessage()

    fun showFinalScore()
}
