package com.example.productivityreward

/*
    Simple class to hold data for a to-do list

 */
class ToDoItem {

    companion object Factory
    {
        fun createToDoItem(): ToDoItem = ToDoItem()
    }

    var UID: String? = null
    var itemDataText: String? = null
    var points: Int = 0
    var done: Boolean? = false

}