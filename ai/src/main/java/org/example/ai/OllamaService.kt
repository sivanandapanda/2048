package org.example.ai

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.example.ai.MoveSuggestionHelper.getMoveSuggestionPrompt
import org.example.ai.MoveSuggestionHelper.parseMoveSuggestionResponse
import org.example.model.MoveSuggestion
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

internal class OllamaService(
    private val modelName: String,
) : AIService {
    private val mapper = jacksonObjectMapper()
    private val apiUrl = "http://localhost:11434/api/generate"

    override suspend fun getSuggestedMove(board: Array<Array<Int?>>): MoveSuggestion? {
        val client = HttpClient.newHttpClient()

        val payload =
            OllamaRequest(
                model = modelName,
                prompt = getMoveSuggestionPrompt(board),
                stream = false,
            )

        return try {
            val requestBody = mapper.writeValueAsString(payload)
            val request =
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            if (response.statusCode() == 200) {
                val responsePayload = mapper.readValue(response.body(), OllamaResponse::class.java)
                parseMoveSuggestionResponse(responsePayload.response.trim())
            } else {
                println("API request failed with status code: ${response.statusCode()}")
                println("Response body: ${response.body()}")
                null
            }
        } catch (e: Exception) {
            println("Error during API call to Ollama: ${e.message}")
            null
        }
    }
}

private data class OllamaRequest(
    val model: String,
    val prompt: String,
    val stream: Boolean,
)

@JsonIgnoreProperties(ignoreUnknown = true)
private data class OllamaResponse(
    val response: String,
)
