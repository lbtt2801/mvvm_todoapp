package com.lbtt2801.mvvm_todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.mvvm_todoapp.model.AppToDoTasks
import kotlin.math.sign
import kotlin.text.*

class StatisticViewModel : ViewModel() {
    private val _action = MutableLiveData<String>()
    val action: LiveData<String>
        get() = _action

    private val _complete = MutableLiveData<String>()
    val complete: LiveData<String>
        get() = _complete

    private fun countTaskComplete(): Float {
        val totalTask = AppToDoTasks.todoTasks.size
        if (totalTask == 0)
            return 0F
        var count = 0
        for(item in AppToDoTasks.todoTasks) {
            if (item.isCompleted)
                count += 1
        }

        return count.toFloat() / totalTask * 100
    }

    fun getActionTask() {
        var numAction = 0F
        if (countTaskComplete() > 0F) {
            numAction = 100 - countTaskComplete()
        }
        _action.postValue(String.format("%.1f", numAction))
    }

    fun getCompleteTask() {
        val numComplete = countTaskComplete()
        _complete.postValue(String.format("%.1f", numComplete))
    }
}