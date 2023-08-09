package com.lbtt2801.mvvm_todoapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lbtt2801.mvvm_todoapp.R
import com.lbtt2801.mvvm_todoapp.databinding.ListTaskBinding
import com.lbtt2801.mvvm_todoapp.model.AppToDoTasks
import com.lbtt2801.mvvm_todoapp.model.Task


class TodoAdapter(private val lst: List<Task>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val listTaskBinding: ListTaskBinding): RecyclerView.ViewHolder(listTaskBinding.root) {
        val todoChk: CheckBox = itemView.findViewById(R.id.todoChk)
        val tvTask: TextView = itemView.findViewById(R.id.tvTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_task, parent, false)
        )

    override fun getItemCount(): Int {
        return lst.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.listTaskBinding.task = lst[position]
        if (holder.listTaskBinding.todoChk.isChecked == !lst[position].isCompleted)
            holder.tvTask.paintFlags = holder.tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        holder.todoChk.setOnClickListener {
            if (holder.todoChk.isChecked) {
                AppToDoTasks.todoTasks[position].isCompleted = true
//                lst[position].isCompleted = true
                holder.tvTask.paintFlags = holder.tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else {
                AppToDoTasks.todoTasks[position].isCompleted = false
//                lst[position].isCompleted = false
                holder.tvTask.paintFlags = holder.tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }
}