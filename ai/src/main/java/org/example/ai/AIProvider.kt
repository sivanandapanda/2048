package org.example.ai

object AIProvider {
    fun getAIService(): AIService = OllamaService("gemma3")
}
