package com.project.todolist.adaptor.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.adaptor.data.Section
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ItemSectionBinding

class SectionAdaptor : RecyclerView.Adapter<SectionAdaptor.TodoSectionViewHolder>() {

    private var todoList: List<Section> = listOf()

    private lateinit var onItemCheckBoxClickListener: TodoListAdaptor.OnItemCheckBoxClickListener
    private lateinit var onItemStarClickListener: TodoListAdaptor.OnItemStarClickListener
    private lateinit var onItemClickListener: TodoListAdaptor.OnItemClickListener

    fun setOnItemCheckBoxClickListener(onItemCheckBoxClickListener: TodoListAdaptor.OnItemCheckBoxClickListener) {
        this.onItemCheckBoxClickListener = onItemCheckBoxClickListener
    }

    fun setOnItemStarClickListener(onItemStarClickListener: TodoListAdaptor.OnItemStarClickListener) {
        this.onItemStarClickListener = onItemStarClickListener
    }

    fun setOnItemClickListener(onItemClickListener: TodoListAdaptor.OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setSection(todoList: List<TodoListEntity>) {
        this.todoList = listOf(Section(0, listOf("중요", "완료 한 일")), Section(1, todoList))
        notifyDataSetChanged()
    }

    inner class TodoSectionViewHolder(private val binding: ItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(section: Section) {
            when (section.type){
                0 -> {
                    binding.todoList.adapter = FocusAdaptor().apply {
                        setFocusList(section.data as List<String>)
                    }
                    binding.todoList.layoutManager = LinearLayoutManager(binding.root.context)

                }
                else -> {
                    binding.todoList.adapter = TodoListAdaptor().apply {
                        setOnItemCheckBoxClickListener(onItemCheckBoxClickListener)
                        setOnItemStarClickListener(onItemStarClickListener)
                        setOnItemClickListener(onItemClickListener)
                        setTodoList(section.data as List<TodoListEntity>)
                    }
                    binding.todoList.layoutManager = LinearLayoutManager(binding.root.context)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return todoList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSectionViewHolder {
        return TodoSectionViewHolder(ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: TodoSectionViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}