package com.project.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoListDao {
    @Query("SELECT * FROM todo_list ORDER BY id DESC")
    fun getAllTodoList(): LiveData<List<TodoListEntity>>

    @Insert
    fun insert(todoListEntity: TodoListEntity)

    @Delete
    fun delete(todoListEntity: TodoListEntity)
}