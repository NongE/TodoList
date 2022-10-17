package com.project.todolist.adaptor.todo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.activity.DoneActivity
import com.project.todolist.activity.ImportantActivity
import com.project.todolist.R
import com.project.todolist.databinding.ItemFocusBinding

class FocusAdaptor : RecyclerView.Adapter<FocusAdaptor.TodoSettingViewHolder>() {

    private var focusList: List<String> = listOf()

    fun setFocusList(focusList: List<String>) {
        this.focusList = focusList
        notifyDataSetChanged()
    }

    inner class TodoSettingViewHolder(private val binding: ItemFocusBinding) :
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

        fun bind(setting: String) {
            binding.todo.text = setting
            when (setting) {
                "중요" -> {
                    binding.img.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                else -> {
                    binding.img.setImageResource(R.drawable.ic_baseline_todo_done_24)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSettingViewHolder {
        return TodoSettingViewHolder(
            ItemFocusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoSettingViewHolder, position: Int) {
        holder.bind(focusList[position])
    }

    override fun getItemCount(): Int {
        return focusList.size
    }
}