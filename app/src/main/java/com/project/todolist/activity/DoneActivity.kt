package com.project.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.todolist.adaptor.done.DoneListAdaptor
import com.project.todolist.adaptor.todo.TodoListAdaptor
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ActivityDoneBinding
import com.project.todolist.viewmodel.TodoListViewModel
import com.project.todolist.viewmodel.TodoListViewModelFactory
import java.util.*

class DoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoneBinding
    private lateinit var adapter: DoneListAdaptor

    private val todoListViewModel: TodoListViewModel by lazy {
        ViewModelProvider(this, TodoListViewModelFactory(this)).get(TodoListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DoneListAdaptor().apply {
            setOnItemCheckBoxClickListener(object : DoneListAdaptor.OnItemCheckBoxClickListener {
                override fun onCheck(v: View, doneListEntity: DoneListEntity) {
                    Toast.makeText(
                        this@DoneActivity,
                        "${doneListEntity.todo} 할 일을 삭제하였어요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    todoListViewModel.deleteDone(doneListEntity)
                }
            })
        }

        binding.doneList.adapter = adapter
        binding.doneList.layoutManager = LinearLayoutManager(this)

        todoListViewModel.getAllDoneList().observe(this, Observer {
            adapter.setDoneList(it)
        })
    }
}