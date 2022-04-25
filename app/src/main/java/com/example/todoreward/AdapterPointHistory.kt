package com.example.todoreward

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AdapterPointHistory(listHistory: MutableList<ItemHistoryPoint>) :
    RecyclerView.Adapter<AdapterPointHistory.HistoryViewHolder>() {

    private var pointList = listHistory

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {

        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_historypoint, viewGroup, false)
        //Set height of all rows
        val lp = view.layoutParams
        lp.height = viewGroup.height / ROWS_IN_LIST
        return HistoryViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: HistoryViewHolder, position: Int) {
        var historyPoint = pointList[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val historyTextData = historyPoint.historyText

        var prefix =
            if (historyPoint.historyPtType == ItemHistoryPoint.Factory.HistoryPtType.Task) "Completed" else "Redeemed"
        var pointChange = ""
        if (historyPoint.pointValue > 0) {
            pointChange =
                if (historyPoint.historyPtType == ItemHistoryPoint.Factory.HistoryPtType.Task) "+${historyPoint.pointValue}" else {
                    "-${historyPoint.pointValue}"
                }
        }
        viewHolder.textLabel.text =
            "${prefix} '${historyPoint.historyText}' for ${pointChange} pts on ${historyPoint.date}"

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = pointList.size

    class HistoryViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val textLabel: TextView = row.findViewById(R.id.historyPtText) as TextView
    }


}