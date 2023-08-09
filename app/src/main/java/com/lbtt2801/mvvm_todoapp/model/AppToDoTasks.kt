package com.lbtt2801.mvvm_todoapp.model

object AppToDoTasks {
    var todoTasks = mutableListOf<Task>()
    var position = -1

    init {
        AppToDoTasks.todoTasks = mutableListOf(
            Task("Task 001", "aaaaaaaaa", false),
            Task("Task 002", "bbbbbbbbb", true),
            Task("Task 003", "ccccccccc", false),
            Task("Task 004", "ddddddddd", false),
            Task("Task 005", "eeeeeeeee", false)
        )
    }
}

