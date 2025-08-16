package org.example.model

data class MoveSuggestion(
    val direction: MoveDirection,
    val confidence: String,
    val reasoning: String,
    val alternativeOptions: List<String>,
) {
    override fun toString(): String =
        """
        Move Suggestion: $direction
        Confidence: $confidence
        Reasoning: $reasoning
        Alternative Options: ${alternativeOptions.joinToString(", ")}
        """.trimIndent()
}
