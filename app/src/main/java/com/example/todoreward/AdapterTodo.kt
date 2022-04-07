package com.example.todoreward


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


const val ROWS_IN_LIST: Int = 7

/*
In charge of taking the list of to do items and applying them to the list view
 */
class AdapterToDo(todolist: MutableList<ItemToDo>) :
    RecyclerView.Adapter<AdapterToDo.ViewHolder>() {

    private var itemList = todolist

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item_todo, viewGroup, false)
        //Set height of all rows
        val lp = view.layoutParams
        lp.height = viewGroup.height / ROWS_IN_LIST
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val UID: String = itemList[position].UID as String
        val itemTextData = itemList[position].taskName as String
        val itemPts = itemList[position].points

        viewHolder.textLabel.text = itemTextData
        viewHolder.ptLabel.text = "+ $itemPts pts"

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        val foreground : RelativeLayout = row.findViewById(R.id.foreground) as RelativeLayout
        val background : RelativeLayout = row.findViewById(R.id.background) as RelativeLayout


        val textLabel: TextView = row.findViewById(R.id.item_text) as TextView
        val ptLabel: TextView = row.findViewById(R.id.pointsGiven) as TextView
    }
}