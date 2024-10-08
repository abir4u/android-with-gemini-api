package com.example.aiwithjetpack.chatbot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class TextToText {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun TextChatBot() {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        val controller = LocalSoftwareKeyboardController.current

        fun submit() {
            controller?.hide()
            val prompt = inputValue

            val generativeModel =
                GenerativeModel(
                    // Specify a Gemini model appropriate for your use case
                    modelName = "gemini-1.5-flash",
                    // Access your API key as a Build Configuration variable (see "Set up your API key" above)
                    apiKey = "AIzaSyA8B7SX-NoAF-KT7NoOtJBHo1NTGhvVaiM")

            runBlocking {
                val response = generativeModel.generateContent(prompt)
                outputValue = response.text.toString()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "Let's chat")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                },
                label = { Text(text = "Type here") }
            )
            Spacer(modifier = Modifier.height(6.dp))
            Button(modifier = Modifier.padding(6.dp),
                onClick = {
                    submit()
                }
            ) {
                Text(text = "Submit")
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Response:", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = outputValue, style = MaterialTheme.typography.bodySmall)
        }
    }
}