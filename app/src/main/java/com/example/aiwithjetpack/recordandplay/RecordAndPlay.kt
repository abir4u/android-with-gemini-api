package com.example.aiwithjetpack.recordandplay

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aiwithjetpack.recordandplay.play.AndroidAudioPlayer
import com.example.aiwithjetpack.recordandplay.record.AndroidAudioRecorder
import java.io.File

class RecordAndPlay {
    @Composable
    fun VoiceRecorder(applicationContext: Context, cacheDir: File) {

        val recorder by lazy {
            AndroidAudioRecorder(applicationContext)
        }

        val player by lazy {
            AndroidAudioPlayer(applicationContext)
        }

        var audioFile: File? = null

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                File(cacheDir, "audio.mp3").also {
                    recorder.start(it)
                    audioFile = it
                }
            }) {
                Text(text = "Start recording")
            }
            Button(onClick = {
                recorder.stop()
            }) {
                Text(text = "Stop recording")
            }
            Button(onClick = {
                player.playFile(audioFile ?: return@Button)
            }) {
                Text(text = "Play")
            }
            Button(onClick = {
                player.stop()
            }) {
                Text(text = "Stop playing")
            }

        }
    }

}