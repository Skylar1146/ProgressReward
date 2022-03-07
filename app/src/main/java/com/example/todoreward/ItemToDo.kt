package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
    Simple class to hold data for a to-do list

 */
class ItemToDo  {

    companion object Factory
    {
        fun createToDoItem(): ItemToDo = ItemToDo()
    }

    var UID: String? = null
    var itemDataText: String? = null
    var points: Int = 0
    var done: Boolean? = false

}

class TodoItemModel: ViewModel()
{
    private val mutableToDoItem = MutableLiveData<ItemToDo>()
    val selectedItemToDo : LiveData<ItemToDo> get() = mutableToDoItem

    fun setItem(todoItemToDo:ItemToDo)
    {
        mutableToDoItem.value = todoItemToDo
    }

}