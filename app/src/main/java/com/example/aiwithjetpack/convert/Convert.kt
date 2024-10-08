package com.example.aiwithjetpack.convert

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aiwithjetpack.chatbot.GetResponse
import java.util.Locale

class Convert {
    @Composable
    fun SpeechToText() {
        var speechText by remember { mutableStateOf("") }
//        var aiResponse by remember { mutableStateOf("") }

        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                speechText = result?.get(0) ?: "No speech detected."
            } else {
                speechText = "[Speech recognition failed.]"
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                try {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Talk to me")
                    launcher.launch(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }) {
                Text(text = "Let's talk")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "You said:", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = speechText, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.padding(16.dp))

            val aiResponse = GetResponse().textChatBot(speechText)
            Text(text = "Response:", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = aiResponse, style = MaterialTheme.typography.bodySmall)
        }
    }
}