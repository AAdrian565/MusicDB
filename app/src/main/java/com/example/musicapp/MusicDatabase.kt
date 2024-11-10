package com.example.musicapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Music::class],
    version = 1
)

//butuh dependency:
//val room_version = "2.6.1"
//
//implementation("androidx.room:room-runtime:$room_version")
//annotationProcessor("androidx.room:room-compiler:$room_version")
//
//// To use Kotlin annotation processing tool (kapt)
//kapt("androidx.room:room-compiler:$room_version")
//NOTE: Membuat database dengan nama Music_DB dan membuat fungsi yang mengembalikan MusicDao
abstract class MusicDatabase: RoomDatabase() {
    companion object{
        const val NAME = "Music_DB"
    }

    abstract fun getMusicDao(): MusicDao
}