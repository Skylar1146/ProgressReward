package com.example.productivityreward

interface UpdateAndDelete {
    fun setIsDoneState(itemUID : String, isDone : Boolean)
    fun onItemDelete(ItemUID:String)
}