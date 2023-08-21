package com.androidrider.notes.Repository  // 4th step -> Repository Class

import androidx.lifecycle.LiveData
import com.androidrider.notes.Dao.NotesDao
import com.androidrider.notes.ModelClass.NotesModel

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes(): LiveData<List<NotesModel>> = dao.getNotes()

    // priority
    fun getHighNotes(): LiveData<List<NotesModel>> = dao.getHighNotes()
    fun getMediumNotes(): LiveData<List<NotesModel>> = dao.getMediumNotes()
    fun getLowNotes(): LiveData<List<NotesModel>> = dao.getLowNotes()

    fun insertNotes(notes: NotesModel){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun udateNotes(notes: NotesModel){
        dao.updateNotes(notes)
    }

}