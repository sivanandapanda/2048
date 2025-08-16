package org.example.ai

import org.example.model.MoveSuggestion

interface AIService {
    suspend fun getSuggestedMove(board: Array<Array<Int?>>): MoveSuggestion?
}
