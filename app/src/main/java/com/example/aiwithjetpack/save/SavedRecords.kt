package com.example.aiwithjetpack.save

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.aiwithjetpack.backend.crud.MongoDBCreate.insertDocument

class SavedRecords {
    @Composable
    fun SaveDocument() {
        Button(onClick = {
            // Test MongoDB integration here.
        }) {
            Text(text = "Save document")
        }
    }
}