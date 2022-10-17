package com.project.todolist.adaptor.done

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.R
import com.project.todolist.adaptor.data.Section
import com.project.todolist.adaptor.todo.TodoListAdaptor
import com.project.todolist.database.done.DoneListEntity
import com.project.todolist.database.todo.TodoListEntity
import com.project.todolist.databinding.ItemDoneBinding
import com.project.todolist.databinding.ItemTodoBinding

class DoneListAdaptor : RecyclerView.Adapter<DoneListAdaptor.DoneListViewHolder>() {

    private var doneList: List<DoneListEntity> = listOf()

    private lateinit var onItemCheckBoxClickListener: OnItemCheckBoxClickListener

    interface OnItemCheckBoxClickListener {
        fun onCheck(v: View, doneListEntity: DoneListEntity)
    }

    fun setOnItemCheckBoxClickListener(onItemCheckBoxClickListener: OnItemCheckBoxClickListener) {
        this.onItemCheckBoxClickListener = onItemCheckBoxClickListener
    }

    fun setDoneList(doneList: List<DoneListEntity>) {
        this.doneList = doneList
        notifyDataSetChanged()
    }

    inner class DoneListViewHolder(private val binding: ItemDoneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doneListEntity: DoneListEntity) {
            binding.todo.text = doneListEntity.todo
            binding.createDate.text = doneListEntity.createDate
            binding.doneDate.text = doneListEntity.doneDate

            binding.todoDoneCheckBox.setOnClickListener {
                onItemCheckBoxClickListener.onCheck(it, doneListEntity)
            }
        }

        fun getBinding(): ItemDoneBinding {
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneListViewHolder {
        return DoneListViewHolder(
            ItemDoneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoneListViewHolder, position: Int) {
        holder.bind(doneList[position])
    }

    override fun onViewRecycled(holder: DoneListViewHolder) {
        super.onViewRecycled(holder)
        holder.getBinding().todoDoneCheckBox.isChecked = false
    }

    override fun getItemCount(): Int {
        return doneList.size
    }
}