package com.project.todolist.database.done

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DoneListDao {
    @Query("SELECT * FROM done_list ORDER BY id DESC")
    fun getAllDoneList(): LiveData<List<DoneListEntity>>

    @Insert
    fun insertDone(doneListEntity: DoneListEntity)

    @Delete
    fun deleteDone(doneListEntity: DoneListEntity)
}