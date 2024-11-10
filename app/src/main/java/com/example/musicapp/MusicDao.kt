package com.example.musicapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

// Data Access Object
@Dao
interface MusicDao {
//    CREATE
//    READ
//    UPDATE
//    DELETE
//    UPDATE + CREATE = Upsert (Insert, tapi kalau sudah ada title yang sama/artist yang sama
//    maka akan mengupdate data yang sudah ada
    @Upsert
    fun addMusic(music: Music)

    @Query("Delete FROM Music where id = :id")
    fun deleteMusic(id: Int)

    @Query("SELECT * FROM Music")
    fun getAllMusic(): LiveData<List<Music>>
}