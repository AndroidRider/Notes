package com.androidrider.notes.Database // 3rd step -> Database Class

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidrider.notes.Dao.NotesDao
import com.androidrider.notes.ModelClass.NotesModel

@Database(entities = [NotesModel::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE:NotesDatabase?=null

        fun getDatabaseInstance(context:Context):NotesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null ){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance =
                    Room.databaseBuilder(context,NotesDatabase::class.java,"Notes")
                        .allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}