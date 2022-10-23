package com.project.todolist.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.todolist.adaptor.todo.TodoListAdaptor
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ActivityMainBinding
import com.project.todolist.databinding.DialogAddTodoBinding
import com.project.todolist.databinding.DialogEditTodoBinding
import com.project.todolist.viewmodel.TodoListViewModel
import com.project.todolist.viewmodel.TodoListViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoListAdaptor

    private val todoListViewModel: TodoListViewModel by lazy {
        ViewModelProvider(this, TodoListViewModelFactory(this)).get(TodoListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTodoList()
        initClickListener()

        todoListViewModel.getAllTodoList().observe(this, Observer {
            adapter.setTodoList(it, true)
            binding.todoList.smoothScrollToPosition(0)
        })
    }

    private fun initTodoList() {
        adapter = TodoListAdaptor().apply {

            setOnItemClickListener(object : TodoListAdaptor.OnItemClickListener {
                override fun onCheck(v: View, todoListEntity: TodoListEntity) {
                    editTodoDialog(todoListEntity).show()
                }
            })

            setOnItemCheckBoxClickListener(object : TodoListAdaptor.OnItemCheckBoxClickListener {
                override fun onCheck(v: View, todoListEntity: TodoListEntity) {
                    Toast.makeText(
                        this@MainActivity,
                        "${todoListEntity.todo} 할 일을 완료하였어요.",
                        Toast.LENGTH_SHORT
                    ).show()
                    todoListViewModel.deleteTodo(todoListEntity)

                    todoListViewModel.insertDone(
                        DoneListEntity(
                            null,
                            1,
                            todoListEntity.todo,
                            todoListEntity.createDate,
                            Date().toString()
                        )
                    )
                }
            })

            setOnItemStarClickListener(object : TodoListAdaptor.OnItemStarClickListener {
                override fun onCheck(v: View, todoListEntity: TodoListEntity) {
                    if (todoListEntity.isImportant) {
                        Toast.makeText(
                            this@MainActivity,
                            "${todoListEntity.todo} 을 중요 처리를 해제하였어요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        todoListEntity.isImportant = false
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "${todoListEntity.todo} 을 중요 할 일로 구분하였어요.",
                            Toast.LENGTH_SHORT
                        ).show()
                        todoListEntity.isImportant = true
                    }
                    println(todoListEntity)
                    todoListViewModel.updateTodo(todoListEntity)
                }
            })

        }

        binding.todoList.adapter = adapter
        binding.todoList.layoutManager = LinearLayoutManager(this)
    }

    private fun initClickListener() {
        binding.addTodo.setOnClickListener {
            addTodoDialog().show()
        }
    }

    private fun addTodoDialog(): Dialog {
        val dialogBinding = DialogAddTodoBinding.inflate(layoutInflater)
        val dialog = Dialog(this).apply {
            setContentView(dialogBinding.root)

            window?.attributes.apply {
                this?.width = ((resources.displayMetrics.widthPixels) * 0.9).toInt()
            }
        }

        dialogBinding.addTodo.setOnClickListener {
            if (dialogBinding.todoTitle.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "할 일이 비어있어요.", Toast.LENGTH_SHORT).show()
            } else {
                todoListViewModel.insertTodo(
                    TodoListEntity(
                        0,
                        false,
                        dialogBinding.todoTitle.text.toString(),
                        Date().toString()
                    )
                )
                dialog.dismiss()
            }
        }
        return dialog
    }

    private fun editTodoDialog(todoLostEntity: TodoListEntity): Dialog {
        val dialogBinding = DialogEditTodoBinding.inflate(layoutInflater)
        val dialog = Dialog(this).apply {
            setContentView(dialogBinding.root)

            window?.attributes.apply {
                this?.width = ((resources.displayMetrics.widthPixels) * 0.9).toInt()
            }
        }

        dialogBinding.todoTitle.setText(todoLostEntity.todo)

        dialogBinding.addTodo.setOnClickListener {
            if (dialogBinding.todoTitle.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "할 일이 비어있어요.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "할 일을 수정하였어요.", Toast.LENGTH_SHORT).show()
                todoLostEntity.todo = dialogBinding.todoTitle.text.toString()
                todoListViewModel.updateTodo(todoLostEntity)
                dialog.dismiss()
            }
        }
        return dialog
    }
}