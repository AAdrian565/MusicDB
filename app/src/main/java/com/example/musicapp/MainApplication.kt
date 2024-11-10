package com.example.musicapp

import android.app.Application
import androidx.room.Room

class MainApplication: Application() {
    companion object{
        lateinit var musicDatabase: MusicDatabase
    }

    override fun onCreate() {
        super.onCreate()
        musicDatabase = Room.databaseBuilder(
            applicationContext,
            MusicDatabase::class.java,
            MusicDatabase.NAME
        ).build()
    }
}