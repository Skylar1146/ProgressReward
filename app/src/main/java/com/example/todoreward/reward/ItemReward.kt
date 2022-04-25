package com.example.todoreward.reward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoreward.getRandomString
import com.example.todoreward.ptAmount

class ItemReward {

    companion object Factory {
        fun createRewardItem(): ItemReward = ItemReward()
        fun createRewardItem(name:String,ptCost:Int,hrs:Int,mins:Int) : ItemReward
        {
            var reward = ItemReward()
            reward.UID = getRandomString(6)
            reward.rewardName = name
            reward.pointCost = ptCost
            reward.hours = hrs
            reward.minutes = mins
            return reward
        }


    }

    var UID: String? = null
    var rewardName: String? = null
    var pointCost: Int = 0
    var hours: Int = 0
    var minutes: Int = 0
    var repeatable: Boolean = false


    fun canAfford(): Boolean {
        return ptAmount >= pointCost
    }

}

class RewardItemModel : ViewModel() {
    private val mutableToDoItem = MutableLiveData<ItemReward>()
    private val redeemedRewardItem = MutableLiveData<ItemReward>()

    val selectedItemReward: LiveData<ItemReward> get() = mutableToDoItem
    val redeemedReward: LiveData<ItemReward> get() = redeemedRewardItem

    fun setRewardRedeemed(reward: ItemReward)
    {
        redeemedRewardItem.value = reward
    }

    fun setItem(todoItemReward: ItemReward) {
        mutableToDoItem.value = todoItemReward
    }

}