package com.example.aiwithjetpack.recordandplay.play

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}