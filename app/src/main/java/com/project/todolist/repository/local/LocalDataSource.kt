package com.project.todolist.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.TodoDatabase
import com.project.todolist.database.todo.TodoListEntity

class LocalDataSource(context: Context) {
    private val todoDatabase = TodoDatabase.getDatabase(context)

    private val todoListDao = todoDatabase.todoListDao()
    private val doneListDao = todoDatabase.doneListDao()

    fun getAllTodoList(): LiveData<List<TodoListEntity>> {
        return todoListDao.getAllTodoList()
    }

    fun getAllImportantTodoList(): LiveData<List<TodoListEntity>> {
        return todoListDao.getAllImportantTodoList()
    }

    fun insertTodo(todoListEntity: TodoListEntity) {
        todoListDao.insertTodo(todoListEntity)
    }

    fun deleteTodo(todoListEntity: TodoListEntity) {
        todoListDao.deleteTodo(todoListEntity)
    }

    fun updateTodo(todoListEntity: TodoListEntity) {
        todoListDao.updateTodo(todoListEntity)
    }

    fun getAllDoneList(): LiveData<List<DoneListEntity>> {
        return doneListDao.getAllDoneList()
    }

    fun insertDone(doneListEntity: DoneListEntity) {
        doneListDao.insertDone(doneListEntity)
    }

    fun deleteDone(doneListEntity: DoneListEntity) {
        doneListDao.deleteDone(doneListEntity)
    }
}