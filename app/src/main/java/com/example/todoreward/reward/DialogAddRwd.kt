package com.example.todoreward.reward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todoreward.R
import com.example.todoreward.getRandomString

class DialogAddRwd : DialogFragment() {

    companion object {
        const val TAG = "Dialog Add Reward"
    }

    private val viewModel: RewardItemModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_add_rwd, container, false)

        val hourPicker: NumberPicker = view.findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker: NumberPicker = view.findViewById<NumberPicker>(R.id.minutePicker)
        hourPicker.minValue = 0
        hourPicker.maxValue = 24
        minutePicker.minValue = 0
        minutePicker.maxValue = 59

        var addBut = view.findViewById<Button>(R.id.butAddReward)
        addBut.setOnClickListener()
        {
            val rewardItemData = ItemReward.createRewardItem()

            var taskNameText =
                view.findViewById<TextView>(R.id.dlgTextRewardName)

            rewardItemData.rewardName = taskNameText?.text.toString()
            rewardItemData.UID = getRandomString(Int.SIZE_BITS - 1)

            rewardItemData.hours = hourPicker.value
            rewardItemData.minutes = minutePicker.value

            // Set a new item
            viewModel.setItem(rewardItemData)
            this.dismiss()

        }
        return view
    }


}