package com.example.todoreward

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AdapterReward(listReward: MutableList<ItemReward>) :
    RecyclerView.Adapter<AdapterReward.RewardViewHolder>() {

    private var rewardList = listReward

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RewardViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_reward, viewGroup, false)
        //Set height of all rows
        val lp = view.layoutParams
        lp.height = viewGroup.height / ROWS_IN_LIST
        return RewardViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RewardViewHolder, position: Int) {

        var reward = rewardList[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val UID: String = reward.UID as String
        val itemTextData = reward.rewardName as String
        val itemPts = reward.pointCost
        val hrs = reward.hours
        val mins = reward.minutes
        viewHolder.textLabel.text = itemTextData
        viewHolder.ptLabel.text = "+ $itemPts pts"


        var durStr = ""
        var hoursIncluded = false
        if (hrs > 0) {
            durStr = if (hrs == 1)
                "${hrs}hr"
            else
                "${hrs}hrs"

            hoursIncluded = true
        }

        if (hoursIncluded)
            durStr += " "
        if (mins > 0) {
            durStr += if (mins == 1)
                "${mins}min"
            else
                "${mins}mins"
        }

        viewHolder.durLabel.text = durStr;


//        if (!reward.canAfford())
//            viewHolder.foreground.setBackgroundColor(Color.GRAY)
//        else
//            viewHolder.foreground.setBackgroundColor(Color.parseColor("#FFA500"))

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = rewardList.size

    class RewardViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        val foreground: ConstraintLayout = row.findViewById(R.id.foreground) as ConstraintLayout
        val background: RelativeLayout = row.findViewById(R.id.background) as RelativeLayout


        val textLabel: TextView = row.findViewById(R.id.rewardText) as TextView
        val ptLabel: TextView = row.findViewById(R.id.pointCost) as TextView

        val durLabel: TextView = row.findViewById(R.id.item_duration) as TextView
    }
}