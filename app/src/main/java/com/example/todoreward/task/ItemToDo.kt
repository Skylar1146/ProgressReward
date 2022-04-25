package com.example.todoreward.task

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
        fun nameIsValid(todoItemName:String) : Boolean
        {
            return (todoItemName.isNotEmpty() && todoItemName.length > 2)
        }
        fun pointValueIsValid(points:Int):Boolean
        {
            return points in 1..100;
        }
        fun dateIsValid(date:Calendar):Boolean
        {
            return date.after(Calendar.getInstance())//not older than the current time
        }


    }

    var UID: String? = null
    var taskName: String? = null
    var points: Int = 0
    var done: Boolean? = false
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var hour: Int = 0//todo: set
    var min: Int = 0

    fun getDate() : Calendar
    {
        val userSelectedDateTime =Calendar.getInstance()
        userSelectedDateTime.set(Calendar.YEAR,year)
        userSelectedDateTime.set(Calendar.MONTH,month)
        userSelectedDateTime.set(Calendar.DAY_OF_MONTH,day)
        userSelectedDateTime.set(Calendar.HOUR,hour)
        userSelectedDateTime.set(Calendar.MINUTE,min)



        return userSelectedDateTime
    }



}

class TodoItemModel: ViewModel()
{
    private val mutableToDoItem = MutableLiveData<ItemToDo>()
    private val completedToDoItem = MutableLiveData<ItemToDo>()

    val selectedItemToDo : LiveData<ItemToDo> get() = mutableToDoItem
    val taskCompleted : LiveData<ItemToDo> get() = completedToDoItem


    fun setTaskCompleted(task: ItemToDo)
    {
        completedToDoItem.value = task
    }

    fun setItem(task: ItemToDo)
    {
        mutableToDoItem.value = task
    }

}