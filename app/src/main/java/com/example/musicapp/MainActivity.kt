package com.example.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Membuat sebuah instance dari MusicViewModel untuk digunakan di a applikasi
        val musicViewModel = ViewModelProvider(this)[MusicViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                MusicListPage(musicViewModel)
            }
        }
    }
}

@Composable
fun MusicListPage(viewModel: MusicViewModel) {
//    NOTE: dipakai kalau tidak terhubung dengan database
    val sampleMusicList = listOf(
        Music(id = 1, title = "Music1", artist = "Artist1"),
        Music(id = 2, title = "Music2", artist = "Artist1"),
        Music(id = 3, title = "Music3", artist = "Artist2"),
        Music(id = 4, title = "Music4", artist = "Artist3")
    )
//    NOTE: dipakai kala tidak terhubung dengan database
//    val musicList = sampleMusicList
    val musicList by viewModel.musicList.observeAsState()
//    ^ Butuh dependency:
//    implementation("androidx.compose.runtime:runtime-livedata:1.7.5")
    var titleInput by remember { mutableStateOf("") }
    var artistInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Title") },
            placeholder = { Text("Enter song title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = artistInput,
            onValueChange = { artistInput = it },
            label = { Text("Artist") },
            placeholder = { Text("Enter artist name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
//                Memanggil fungsi dari MusicViewModel yang bernama addMusic
//                Data ini diambil dari textbox di atas
                viewModel.addMusic(titleInput, artistInput)
                titleInput = ""
                artistInput = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        musicList?.let {
            LazyColumn {
                itemsIndexed(it) { index, item ->
                    MusicItem(
                        item = item,
                        onDelete = {
//                            Membuat fungsi inline yang memanggil viewModel untuk menghapus
//                            data di database berdasarkan id yang diberikan
                            viewModel.deleteMusic(item.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MusicItem(
    item: Music,
//    deklarasi argument onDelete
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Cyan)
            .border(1.dp, Color.Gray, MaterialTheme.shapes.medium)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = item.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.artist,
                fontSize = 16.sp,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
//            Memanggil fungsi yang sudah didefinisikan di Line 119 (Butuh deklarasi di line 131
            onClick = onDelete,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(painterResource(id = R.drawable.baseline_ad_units_24), contentDescription = "Delete")
        }
    }
}