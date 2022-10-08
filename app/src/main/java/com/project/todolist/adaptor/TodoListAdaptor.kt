package com.project.todolist.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.database.TodoListEntity
import com.project.todolist.databinding.ItemTodoBinding

class TodoListAdaptor: RecyclerView.Adapter<TodoListAdaptor.TodoListViewHolder>() {

    private var todoList: List<TodoListEntity> = listOf()

    private lateinit var onItemCheckBoxClickListener: OnItemCheckBoxClickListener

    interface OnItemCheckBoxClickListener {
        fun onCheck(v: View, todoListEntity: TodoListEntity)
    }

    fun setOnItemCheckBoxClickListener(onItemCheckBoxClickListener: OnItemCheckBoxClickListener) {
        this.onItemCheckBoxClickListener = onItemCheckBoxClickListener
    }

    inner class TodoListViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todoListEntity: TodoListEntity) {
            binding.todo.text = todoListEntity.todo
            binding.createDate.text = todoListEntity.createDate

            binding.todoDoneCheckBox.setOnClickListener {
                onItemCheckBoxClickListener.onCheck(it, todoListEntity)
            }
        }

        fun getBinding(): ItemTodoBinding{
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onViewRecycled(holder: TodoListViewHolder) {
        super.onViewRecycled(holder)
        holder.getBinding().todoDoneCheckBox.isChecked = false
    }

    fun setTodoList(todoList: List<TodoListEntity>){
        this.todoList = todoList
        notifyDataSetChanged()
    }
}