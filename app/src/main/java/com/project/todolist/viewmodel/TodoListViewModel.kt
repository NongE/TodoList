package com.project.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.project.todolist.repository.TodoListRepository
import com.project.todolist.database.TodoListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(context: Context) : ViewModel() {

    private val todoListRepository = TodoListRepository.getInstance(context)

    fun getAllTodoList(): LiveData<List<TodoListEntity>> {
        return todoListRepository.getAllTodoList()
    }

    fun insert(todoListEntity: TodoListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.insert(todoListEntity)
        }
    }

    fun delete(todoListEntity: TodoListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            todoListRepository.delete(todoListEntity)
        }
    }
}