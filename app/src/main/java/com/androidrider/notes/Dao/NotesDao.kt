package com.androidrider.notes.Dao // 2nd step -> Dao Class

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.androidrider.notes.ModelClass.NotesModel


@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<NotesModel>>

// priority Query
    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediumNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<NotesModel>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: NotesModel)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: NotesModel)

}