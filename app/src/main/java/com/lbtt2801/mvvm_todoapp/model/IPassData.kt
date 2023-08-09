package com.lbtt2801.mvvm_todoapp.model

interface IPassData {
    fun exchangeData(title: String, detail: String)
    fun changeDataItem(title: String, detail: String)
}