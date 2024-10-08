package com.example.aiwithjetpack.chatbot

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class GetResponse {
    fun textChatBot(inputText: String): String {
        if (inputText.isEmpty()) {
            return ""
        }

        var outputValue = ""

        val generativeModel =
            GenerativeModel(
                // Specify a Gemini model appropriate for your use case
                modelName = "gemini-1.5-flash",
                // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                apiKey = "AIzaSyA8B7SX-NoAF-KT7NoOtJBHo1NTGhvVaiM")

        runBlocking {
            val response = generativeModel.generateContent(inputText)
            outputValue = response.text.toString()
        }

        return outputValue
    }
}