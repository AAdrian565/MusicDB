package com.example.musicapp

import androidx.room.Entity
import androidx.room.PrimaryKey

// NOTE: Object yang bernama "Music" yang dipakai sebagai tipe data di dalam applikasi
@Entity
data class Music(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artist: String,
)