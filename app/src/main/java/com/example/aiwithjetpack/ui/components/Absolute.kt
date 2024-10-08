package com.example.aiwithjetpack.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.aiwithjetpack.chatbot.TextToText
import com.example.aiwithjetpack.convert.Convert
import com.example.aiwithjetpack.recordandplay.RecordAndPlay
import com.example.aiwithjetpack.save.SavedRecords
import java.io.File

class Absolute {
    @Composable
    fun TabScreen(applicationContext: Context, cacheDir: File) {
        var tabIndex by remember { mutableIntStateOf(0) }

        val tabs = listOf("Talk", "Type", "Record", "Save")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> Convert().SpeechToText()
                1 -> TextToText().TextChatBot()
                2 -> RecordAndPlay().VoiceRecorder(applicationContext = applicationContext, cacheDir)
                3 -> SavedRecords().SaveDocument()
            }
        }
    }
}