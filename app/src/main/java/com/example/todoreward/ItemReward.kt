package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemReward {

    companion object Factory {
        fun createRewardItem(): ItemReward = ItemReward()
    }

    var UID: String? = null
    var rewardName: String? = null
    var pointCost: Int = 0

    fun canAfford(): Boolean {
        return ptAmount >= pointCost
    }

}

class RewardItemModel : ViewModel() {
    private val mutableToDoItem = MutableLiveData<ItemReward>()
    val selectedItemReward: LiveData<ItemReward> get() = mutableToDoItem

    fun setItem(todoItemReward: ItemReward) {
        mutableToDoItem.value = todoItemReward
    }

}