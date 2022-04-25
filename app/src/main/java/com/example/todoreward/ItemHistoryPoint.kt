package com.example.todoreward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemHistoryPoint {

    companion object Factory {
        enum class HistoryPtType {
            Task,
            Reward
        }

        fun createHistoryPoint(): ItemHistoryPoint = ItemHistoryPoint()
        fun createHistoryPointForTask(
            text: String?,
            pointValue: Int,
            date: String,
        ): ItemHistoryPoint {
            var history = ItemHistoryPoint()
            history.historyText = text
            history.pointValue = pointValue
            history.date = date
            history.historyPtType = HistoryPtType.Task

            return history
        }
        fun createHistoryPointForReward(
            text: String?,
            pointValue: Int,
            date: String,
        ): ItemHistoryPoint {
            var history = ItemHistoryPoint()
            history.historyText = text
            history.pointValue = pointValue
            history.date = date
            history.historyPtType = HistoryPtType.Reward

            return history
        }
    }


    var historyText: String? = null
    var historyPtType: HistoryPtType = HistoryPtType.Task
    var pointValue: Int = 0
    var date: String? = null

}

class ItemHistoryPointModel : ViewModel() {
    private val mutableHistoryPoint = MutableLiveData<ItemHistoryPoint>()
    val selectedItemToDo: LiveData<ItemHistoryPoint> get() = mutableHistoryPoint

    fun setItem(itemHistoryPoint: ItemHistoryPoint) {
        mutableHistoryPoint.value = itemHistoryPoint
    }

}