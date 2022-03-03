package com.example.todoreward

interface UpdateAndDelete {
    fun setIsDoneState(itemUID : String, isDone : Boolean)
    fun onItemDelete(ItemUID:String)
}