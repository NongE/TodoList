package com.project.todolist.database.todo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todo_list ORDER BY id DESC")
    fun getAllTodoList(): LiveData<List<TodoListEntity>>

    @Query("SELECT * FROM todo_list WHERE isImportant = 1 ORDER BY id DESC")
    fun getAllImportantTodoList(): LiveData<List<TodoListEntity>>

    @Insert
    fun insertTodo(todoListEntity: TodoListEntity)

    @Delete
    fun deleteTodo(todoListEntity: TodoListEntity)

    @Update
    fun updateTodo(todoListEntity: TodoListEntity)
}