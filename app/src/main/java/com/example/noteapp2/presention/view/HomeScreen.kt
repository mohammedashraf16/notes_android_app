package com.example.noteapp2.presention.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp2.presention.viewModel.NoteViewModel

@Composable
fun HomeScreenContent(noteViewModel: NoteViewModel){

    val allNotes = noteViewModel.allNotes.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(allNotes.value?: emptyList()){note->
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF03bdd7) ),elevation = CardDefaults.cardElevation(10.dp),modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.push(NoteDetailsScreen(note, noteViewModel)) }
                .padding(16.dp)) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                noteViewModel.updateNote(note.copy(isFavorite = !note.isFavorite))
                            }
                            .align(Alignment.End).size(30.dp),imageVector = Icons.Filled.Star, contentDescription = null, tint = if (note.isFavorite) Color.Yellow else  Color.White)
                    Text(text = note.title, fontSize = 20.sp,modifier = Modifier.align(Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = note.description, fontSize = 20.sp)
                }

            }
        }
    }


}