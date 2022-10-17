package com.project.todolist.adaptor.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.R
import com.project.todolist.adaptor.data.Section
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ItemTodoBinding

class TodoListAdaptor: RecyclerView.Adapter<TodoListAdaptor.TodoListViewHolder>() {

    private var todoList: List<TodoListEntity> = listOf()

    private lateinit var onItemCheckBoxClickListener: OnItemCheckBoxClickListener
    private lateinit var onItemStarClickListener: OnItemStarClickListener
    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemCheckBoxClickListener {
        fun onCheck(v: View, todoListEntity: TodoListEntity)
    }

    interface OnItemStarClickListener {
        fun onCheck(v: View, todoListEntity: TodoListEntity)
    }

    interface OnItemClickListener {
        fun onCheck(v: View, todoListEntity: TodoListEntity)
    }

    fun setOnItemCheckBoxClickListener(onItemCheckBoxClickListener: OnItemCheckBoxClickListener) {
        this.onItemCheckBoxClickListener = onItemCheckBoxClickListener
    }

    fun setOnItemStarClickListener(onItemStarClickListener: OnItemStarClickListener) {
        this.onItemStarClickListener = onItemStarClickListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setTodoList(todoList: List<TodoListEntity>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    inner class TodoListViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onCheck(it, todoList[adapterPosition])
            }
        }

        fun bind(todoListEntity: TodoListEntity) {
            binding.todo.text = todoListEntity.todo
            binding.createDate.text = todoListEntity.createDate

            if (todoListEntity.isImportant){
                binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_24)
            }else{
                binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }

            binding.todoDoneCheckBox.setOnClickListener {
                onItemCheckBoxClickListener.onCheck(it, todoListEntity)
            }

            binding.importantTodo.setOnClickListener {
                onItemStarClickListener.onCheck(it, todoListEntity)
                if (todoListEntity.isImportant){
                    binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_24)
                }else{
                    binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
            }
        }

        fun getBinding(): ItemTodoBinding{
            return binding
        }
    }

    override fun getItemViewType(position: Int): Int {
        return todoList[position].type
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

    override fun onViewRecycled(holder: TodoListViewHolder) {
        super.onViewRecycled(holder)
        holder.getBinding().todoDoneCheckBox.isChecked = false
        holder.getBinding().importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}