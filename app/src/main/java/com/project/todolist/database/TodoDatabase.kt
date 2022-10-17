package com.project.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.todolist.database.done.DoneListDao
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.todo.TodoListDao
import com.project.todolist.database.todo.TodoListEntity

@Database(entities = [DoneListEntity::class, TodoListEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null
        fun getDatabase(context: Context): TodoDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context, TodoDatabase::class.java, "todo_database").build()
                }
            }
            return instance!!
        }
    }

    abstract fun todoListDao(): TodoListDao
    abstract fun doneListDao(): DoneListDao
}