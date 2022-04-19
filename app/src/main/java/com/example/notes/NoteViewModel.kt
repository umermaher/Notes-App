package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes:LiveData<List<Note>>
    private  val repository:NoteRepository

    init {
        val noteDao  = NoteRoomDatabase.getDatabase(application).getNoteDoa()
        repository = NoteRepository(noteDao)
        allNotes=repository.allNotes
    }

    fun delete(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun insert(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}
