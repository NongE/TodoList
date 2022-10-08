package com.project.todolist.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.project.todolist.database.TodoListDatabase
import com.project.todolist.database.TodoListEntity

class LocalDataSource(context: Context) {
    private val todoListDatabase = TodoListDatabase.getDatabase(context)
    private val todoListDao = todoListDatabase.todoListDao()

    fun getAllTodoList(): LiveData<List<TodoListEntity>> {
        return todoListDao.getAllTodoList()
    }

    fun insert(todoListEntity: TodoListEntity) {
        todoListDao.insert(todoListEntity)
    }

    fun delete(todoListEntity: TodoListEntity) {
        todoListDao.delete(todoListEntity)
    }
}