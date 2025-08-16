package org.example.ai

import org.example.model.MoveDirection
import org.example.model.MoveSuggestion

internal object MoveSuggestionHelper {
    fun getMoveSuggestionPrompt(board: Array<Array<Int?>>): String =
        """
        You are an expert 2048 game player. The game is played on a 4x4 grid. The valid moves are W (up), A (left), S (down), and D (right). 
        Your goal is to suggest the single best next move for the given board state.
        
        2048 Game Rules:
            - Tiles slide in the chosen direction and identical adjacent tiles merge
            - Each merge doubles the tile value and adds to score
            - New tile (2 or 4) appears after each move
            - Goal is to reach 2048 tile and maximize score
            - Game ends when board is full and no merges possible
        
        Response Format (be concise):
        MOVE: [UP/DOWN/LEFT/RIGHT]
        CONFIDENCE: [HIGH/MEDIUM/LOW]
        REASON: [One sentence explaining why this move is best]
        ALTERNATIVES: [Other viable moves, if any]
        
        Current board state:
        ${board.joinToString("\n") { it.joinToString(" ") }}
        """.trimIndent()

    fun parseMoveSuggestionResponse(response: String): MoveSuggestion? {
        val lines = response.lines().map { it.trim() }
        if (lines.size < 4) return null

        val moveLine = lines[0]
        val confidence = lines[1].replace("CONFIDENCE: ", "")
        val reason = lines[2].replace("REASON: ", "")
        val alternatives = lines[3].replace("ALTERNATIVES: ", "").split(",").map { it.trim() }

        val move =
            when {
                moveLine.startsWith("MOVE: UP") -> MoveDirection.UP
                moveLine.startsWith("MOVE: DOWN") -> MoveDirection.DOWN
                moveLine.startsWith("MOVE: LEFT") -> MoveDirection.LEFT
                moveLine.startsWith("MOVE: RIGHT") -> MoveDirection.RIGHT
                else -> return null
            }

        return MoveSuggestion(move, confidence, reason, alternatives)
    }
}
