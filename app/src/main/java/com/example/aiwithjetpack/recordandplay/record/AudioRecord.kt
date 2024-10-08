package com.example.aiwithjetpack.recordandplay.record

import java.io.File

interface AudioRecord {
    fun start(outputFile: File)
    fun stop()
}