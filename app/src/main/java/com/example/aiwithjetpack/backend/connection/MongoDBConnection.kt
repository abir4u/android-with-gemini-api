package com.example.aiwithjetpack.backend.connection

import android.annotation.SuppressLint
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients


object MongoDBConnection {
    @SuppressLint("AuthLeak")
//    private const val SRV_CONNECTION_STRING = "mongodb+srv://qa4slog:6I1or42Y4BkrGG4X@lisndemo1.ijzw0.mongodb.net/?retryWrites=true&w=majority&appName=LisnDemo1"
    private const val STANDARD_CONNECTION_STRING = "mongodb://qa4slog:6I1or42Y4BkrGG4X@lisndemo1.ijzw0.mongodb.net/lisn_demo1?retryWrites=true&w=majority"

    fun getMongoClient(): MongoClient {
        return MongoClients.create(STANDARD_CONNECTION_STRING)
    }
}