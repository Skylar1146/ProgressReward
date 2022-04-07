package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

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
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var hour: Int = 0//todo: set
    var min: Int = 0

    fun GetDate() : Calendar
    {
        val userSelectedDateTime =Calendar.getInstance()
        userSelectedDateTime.set(year, month, day, hour , min)

        return userSelectedDateTime
    }

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