package com.project.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val todo: String,
    val createDate: String
)
