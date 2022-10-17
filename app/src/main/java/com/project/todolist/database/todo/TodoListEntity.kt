package com.project.todolist.database.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: Int,
    var isImportant: Boolean,
    var todo: String,
    val createDate: String
)
