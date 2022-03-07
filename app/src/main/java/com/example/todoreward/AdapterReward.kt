package com.example.todoreward

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class AdapterReward(context: Context, listReward: MutableList<ItemReward>) :
    RecyclerView.Adapter<AdapterReward.RewardViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val UID: String = rewardList[position].UID as String
        val itemTextData = rewardList[position].rewardName as String
        val itemPts = rewardList[position].pointCost as Integer

        viewHolder.textLabel.text = itemTextData
        viewHolder.ptLabel.text = "+ $itemPts pts"

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = rewardList.size

    class RewardViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        val foreground : RelativeLayout = row!!.findViewById(R.id.foreground) as RelativeLayout
        val background : RelativeLayout = row!!.findViewById(R.id.background) as RelativeLayout


        val textLabel: TextView = row!!.findViewById(R.id.rewardText) as TextView
        val ptLabel: TextView = row!!.findViewById(R.id.pointCost) as TextView


    }
}