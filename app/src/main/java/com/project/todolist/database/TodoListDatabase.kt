package com.project.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoListEntity::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: TodoListDatabase? = null
        fun getDatabase(context: Context): TodoListDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context, TodoListDatabase::class.java, "todo_list_database").build()
                }
            }
            return instance!!
        }
    }

    abstract fun todoListDao(): TodoListDao
}