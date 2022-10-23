package com.project.todolist.adaptor.todo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.R
import com.project.todolist.activity.DoneActivity
import com.project.todolist.activity.ImportantActivity
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ItemFocusBinding
import com.project.todolist.databinding.ItemTodoBinding

class TodoListAdaptor : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var todoList: List<Any> = listOf()

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

    fun setTodoList(todoList: List<TodoListEntity>, isFocus: Boolean) {
        when (isFocus) {
            true -> this.todoList = listOf("중요", "완료 한 일") + todoList
            false -> this.todoList = todoList
        }
        notifyDataSetChanged()
    }

    inner class FocusViewHolder(private val binding: ItemFocusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition == 1) {
                    val intent = Intent(it.context, DoneActivity::class.java)
                    it.context.startActivity(intent)
                } else {
                    val intent = Intent(it.context, ImportantActivity::class.java)
                    it.context.startActivity(intent)
                }
            }
        }

        fun bind(title: String) {
            binding.todo.text = title
            when (title) {
                "중요" -> {
                    binding.img.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                else -> {
                    binding.img.setImageResource(R.drawable.ic_baseline_todo_done_24)
                }
            }
        }
    }

    inner class TodoListViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onCheck(it, todoList[adapterPosition] as TodoListEntity)
            }
        }

        fun bind(todoListEntity: TodoListEntity) {
            binding.todo.text = todoListEntity.todo
            binding.createDate.text = todoListEntity.createDate

            if (todoListEntity.isImportant) {
                binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }

            binding.todoDoneCheckBox.setOnClickListener {
                onItemCheckBoxClickListener.onCheck(it, todoListEntity)
            }

            binding.importantTodo.setOnClickListener {
                onItemStarClickListener.onCheck(it, todoListEntity)
                if (todoListEntity.isImportant) {
                    binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_24)
                } else {
                    binding.importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
            }
        }

        fun getBinding(): ItemTodoBinding {
            return binding
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (todoList[position] is TodoListEntity) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                return FocusViewHolder(
                    ItemFocusBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                return TodoListViewHolder(
                    ItemTodoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> {
                (holder as FocusViewHolder).bind(todoList[position] as String)
            }

            else -> {
                (holder as TodoListViewHolder).bind(todoList[position] as TodoListEntity)
            }
        }

    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        when (holder.itemViewType) {
            0 -> {

            }

            else -> {
                (holder as TodoListViewHolder).getBinding().todoDoneCheckBox.isChecked = false
                (holder as TodoListViewHolder).getBinding().importantTodo.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}