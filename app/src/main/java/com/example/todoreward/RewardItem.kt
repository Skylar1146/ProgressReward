package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RewardItem {

    companion object Factory
    {
        fun createRewardItem(): RewardItem = RewardItem()
    }

    var UID: String? = null
    var pointCost: Int = 0

}

class RewardItemModel: ViewModel()
{
    private val mutableToDoItem = MutableLiveData<RewardItem>()
    val selectedItem : LiveData<RewardItem> get() = mutableToDoItem

    fun setItem(todoItem:RewardItem)
    {
        mutableToDoItem.value = todoItem
    }

}