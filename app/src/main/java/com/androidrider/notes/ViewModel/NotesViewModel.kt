package com.androidrider.notes.ViewModel // tth step -> ViewModel Class

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.androidrider.notes.Database.NotesDatabase
import com.androidrider.notes.ModelClass.NotesModel
import com.androidrider.notes.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository:NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: NotesModel){
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<NotesModel>> = repository.getAllNotes()

// priority
    fun getHighNotes(): LiveData<List<NotesModel>> = repository.getHighNotes()
    fun getMediumNotes(): LiveData<List<NotesModel>> = repository.getMediumNotes()
    fun getLowNotes(): LiveData<List<NotesModel>> = repository.getLowNotes()

    fun  deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: NotesModel){
        repository.udateNotes(notes)
    }
}