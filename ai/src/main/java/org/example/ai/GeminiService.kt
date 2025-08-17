package org.example.ai

import org.example.model.MoveSuggestion

internal class GeminiService : AIService {
    override suspend fun getSuggestedMove(board: Array<Array<Int?>>): MoveSuggestion? {
        TODO("to be implemented")
    }
}
