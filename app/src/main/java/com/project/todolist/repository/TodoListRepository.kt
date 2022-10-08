package com.project.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.project.todolist.database.TodoListEntity
import com.project.todolist.repository.local.LocalDataSource

class TodoListRepository(context: Context) {

    companion object {
        private var instance: TodoListRepository? = null
        fun getInstance(context: Context): TodoListRepository {
            if (instance == null) {
                instance = TodoListRepository(context)
            }
            return instance!!
        }
    }

    private val localDataSource = LocalDataSource(context)

    fun getAllTodoList(): LiveData<List<TodoListEntity>> {
        return localDataSource.getAllTodoList()
    }

    fun insert(todoListEntity: TodoListEntity) {
        localDataSource.insert(todoListEntity)
    }

    fun delete(todoListEntity: TodoListEntity) {
        localDataSource.delete(todoListEntity)
    }
}