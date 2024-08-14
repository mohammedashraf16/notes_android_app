package com.example.noteapp2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp2.presention.viewModel.NoteViewModel
import com.example.noteapp2.data.model.Note
import com.example.noteapp2.presention.view.AddNoteDialog
import com.example.noteapp2.presention.view.FavoriteScreenContent
import com.example.noteapp2.presention.view.HomeScreenContent
import com.example.noteapp2.ui.theme.NoteApp2Theme

class MainScreen(val noteViewModel: NoteViewModel) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val selectedIndex = remember { mutableStateOf(0) }
        val showAddDialog = remember { mutableStateOf(false) }
        val navigator = LocalNavigator.currentOrThrow

        NoteApp2Theme {
            Scaffold(
                floatingActionButton = {
                    IconButton(modifier = Modifier.size(80.dp),
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Gray, contentColor = Color.White),
                        onClick = {
                            showAddDialog.value = true
                        }) {
                        Icon(modifier = Modifier.size(35.dp),imageVector = Icons.Default.Add, contentDescription =null)
                    }
                },
                topBar = { TopAppBar(
                    actions = {
                        // Action buttons
                        IconButton(onClick = {  }) {
                            Icon(Icons.Default.MoreVert, contentDescription =null, modifier = Modifier.size(50.dp),tint = Color.White )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray, titleContentColor = Color.White)
                    ,
                    title = { Text(text = "MyNotes", fontSize = 30.sp) })
                }
                , bottomBar = { BottomAppBar(containerColor = Color.Gray) {
                    IconButton(modifier = Modifier.weight(1f),onClick = { selectedIndex.value = 0 }) {
                        Icon(modifier = Modifier.size(50.dp),imageVector = Icons.Filled.Home, contentDescription =null, tint = if(selectedIndex.value==0) Color.Black else  Color.White )
                    }
                    IconButton(modifier = Modifier.weight(1f),onClick = { selectedIndex.value = 1  }) {
                        Icon(modifier = Modifier.size(50.dp),imageVector = Icons.Default.Favorite, contentDescription =null, tint = if(selectedIndex.value==1) Color.Black else  Color.White )
                    }

                }
                }
                ,modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    // screen content
                    if(showAddDialog.value){
                        AddNoteDialog(onSaveClick = { x , y ->
                            noteViewModel.insertNote(Note(title = x, description =y , isFavorite = false))
                            showAddDialog.value = false
                        },
                            onDismissRequest = {
                                showAddDialog.value= false
                            })
                    }
                    if (selectedIndex.value == 0) {
                        HomeScreenContent(noteViewModel = noteViewModel)
                    } else {
                        FavoriteScreenContent(noteViewModel = noteViewModel)
                    }
                    // HomeScreenContent(noteViewModel = noteViewModel)
                }
            }
        }
    }
}