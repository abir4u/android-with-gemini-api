package com.example.aiwithjetpack.backend.crud

import android.util.Log
import com.example.aiwithjetpack.backend.connection.MongoDBConnection
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.asFlow
import org.bson.Document

object MongoDBCreate {
    fun insertDocument() = runBlocking(Dispatchers.IO) {
        try {
            val mongoClient: MongoClient = MongoDBConnection.getMongoClient()
            val database: MongoDatabase = mongoClient.getDatabase("lisn_demo1")
            val collection: MongoCollection<Document> = database.getCollection("document")

            // Read
            // Convert the reactive stream (Publisher) into a coroutine flow
            val flow = collection.find().asFlow()

            // Collect the flow (suspend function) to process each document
            flow.collect { document ->
                Log.d("MongoDBRead", "Fetched Document: ${document.toJson()}")
            }

            // Create
//        val document = Document("name", "John Doe")
//            .append("email", "johndoe@example.com")
//            .append("age", "30")
//
//        collection.insertOne(document)

            mongoClient.close()

        } catch (e: Exception) {
            Log.e("MongoDBRead", "Error fetching documents", e)
        }
    }
}