package com.project.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.repository.TodoListRepository
import com.project.todolist.database.todo.TodoListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(context: Context) : ViewModel() {

    private val todoListRepository = TodoListRepository.getInstance(context)
    private val todoList = todoListRepository.getAllTodoList()
    private val importantTodoList = todoListRepository.getAllImportantTodoList()
    private val doneList = todoListRepository.getAllDoneList()

    fun getAllTodoList(): LiveData<List<TodoListEntity>> {
        println(todoList.value)
        return todoList
    }

    fun getAllImportantTodoList(): LiveData<List<TodoListEntity>> {
        return importantTodoList
    }

    fun insertTodo(todoListEntity: TodoListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.insertTodo(todoListEntity)
        }
    }

    fun deleteTodo(todoListEntity: TodoListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.deleteTodo(todoListEntity)
        }
    }

    fun updateTodo(todoListEntity: TodoListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.updateTodo(todoListEntity)
        }
    }

    fun getAllDoneList(): LiveData<List<DoneListEntity>> {
        return doneList
    }

    fun insertDone(doneListEntity: DoneListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.insertDone(doneListEntity)
        }
    }

    fun deleteDone(doneListEntity: DoneListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.deleteDone(doneListEntity)
        }
    }
}