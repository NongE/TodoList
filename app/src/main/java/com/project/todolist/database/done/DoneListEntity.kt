package com.project.todolist.database.done

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "done_list")
data class DoneListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val type: Int,
    val todo: String,
    val createDate: String,
    val doneDate: String
)
