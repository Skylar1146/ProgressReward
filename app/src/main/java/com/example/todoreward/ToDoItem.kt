package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
    Simple class to hold data for a to-do list

 */
class ToDoItem  {

    companion object Factory
    {
        fun createToDoItem(): ToDoItem = ToDoItem()
    }

    var UID: String? = null
    var itemDataText: String? = null
    var points: Int = 0
    var done: Boolean? = false

}

class TodoItemModel: ViewModel()
{
    private val mutableToDoItem = MutableLiveData<ToDoItem>()
    val selectedItem : LiveData<ToDoItem> get() = mutableToDoItem

    fun setItem(todoItem:ToDoItem)
    {
        mutableToDoItem.value = todoItem
    }

}