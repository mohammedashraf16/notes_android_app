package com.example.noteapp2.data.repository

import com.example.noteapp2.data.db.NoteDao
import com.example.noteapp2.data.model.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor( val noteDao: NoteDao){
    suspend fun getAll(): List<Note> {
        return noteDao.getAll()
    }
    suspend fun getFavorites(): List<Note>{
        return noteDao.getFavorites()
    }
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun deleteAll() = noteDao.deleteAll()
    suspend fun getById(id: Int) = noteDao.getById(id)
    suspend fun deleteById(id: Int) = noteDao.deleteById(id)

}