package com.project.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.todo.TodoListEntity
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

    fun getAllImportantTodoList(): LiveData<List<TodoListEntity>> {
        return localDataSource.getAllImportantTodoList()
    }

    fun insertTodo(todoListEntity: TodoListEntity) {
        localDataSource.insertTodo(todoListEntity)
    }

    fun deleteTodo(todoListEntity: TodoListEntity) {
        localDataSource.deleteTodo(todoListEntity)
    }

    fun updateTodo(todoListEntity: TodoListEntity) {
        localDataSource.updateTodo(todoListEntity)
    }

    fun getAllDoneList(): LiveData<List<DoneListEntity>> {
        return localDataSource.getAllDoneList()
    }

    fun insertDone(doneListEntity: DoneListEntity) {
        localDataSource.insertDone(doneListEntity)
    }

    fun deleteDone(doneListEntity: DoneListEntity) {
        localDataSource.deleteDone(doneListEntity)
    }
}