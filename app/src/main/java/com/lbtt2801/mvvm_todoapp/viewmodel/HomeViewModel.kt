package com.lbtt2801.mvvm_todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lbtt2801.mvvm_todoapp.model.AppToDoTasks
import com.lbtt2801.mvvm_todoapp.model.Task

class HomeViewModel() : ViewModel() {

    private var _lstTaskLiveData = MutableLiveData<List<Task>>()
    val lstTaskLiveData: LiveData<List<Task>> get() = _lstTaskLiveData

    var lstTasks = listOf<Task>()

    fun getDataFilter(item: String) {
        when (item) {
            "all" -> lstTasks = AppToDoTasks.todoTasks
            "action" -> lstTasks = AppToDoTasks.todoTasks.filter { !it.isCompleted }
            "complete" -> lstTasks = AppToDoTasks.todoTasks.filter { it.isCompleted }
//            else -> Unit
        }
        _lstTaskLiveData.postValue(lstTasks)
    }


}