package com.example.musicapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// NOTE: Fungsi-fungsi yang dipakai di applikasi
class MusicViewModel: ViewModel() {
    val musicDao = MainApplication.musicDatabase.getMusicDao()
    val musicList: LiveData<List<Music>> = musicDao.getAllMusic()

//    memanggil fungsi yang dibuat di MusicDao
    fun addMusic(title: String, artists: String){
        viewModelScope.launch(Dispatchers.IO) {
            musicDao.addMusic(Music(title = title, artist = artists))
        }
    }

    fun deleteMusic(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            musicDao.deleteMusic(id)
        }
    }
}