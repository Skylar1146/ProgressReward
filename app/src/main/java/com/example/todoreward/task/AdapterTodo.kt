package com.example.todoreward.task


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.todoreward.R
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


const val ROWS_IN_LIST: Int = 7

/*
In charge of taking the list of to do items and applying them to the list view
 */
class AdapterToDo(todolist: MutableList<ItemToDo>) :
    RecyclerView.Adapter<AdapterToDo.ViewHolder>() {

    private var itemList = todolist
    val DAYS_REM_TO_SHOW_DAYS_DUE_In = 6;

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
        val item = itemList[position]
        val UID: String = item.UID as String
        val itemTextData = item.taskName as String
        val itemPts = item.points

        viewHolder.textLabel.text = itemTextData
        viewHolder.ptLabel.text = "+ $itemPts"



        val daysDueIn =   ChronoUnit.DAYS.between(Calendar.getInstance().toInstant(),item.getDate().toInstant())
        var showTimeDuration = daysDueIn >= DAYS_REM_TO_SHOW_DAYS_DUE_In
        viewHolder.durText.visibility = if (showTimeDuration) View.VISIBLE else View.INVISIBLE
        viewHolder.durImg.visibility = if (showTimeDuration) View.VISIBLE else View.INVISIBLE

        if (showTimeDuration) {
            viewHolder.durText.text = "$daysDueIn days"
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {

        val foreground: ConstraintLayout = row.findViewById(R.id.foreground) as ConstraintLayout
        val background: RelativeLayout = row.findViewById(R.id.background) as RelativeLayout


        val textLabel: TextView = row.findViewById(R.id.historyPtText) as TextView
        val ptLabel: TextView = row.findViewById(R.id.pointsGiven) as TextView


        val durText: TextView = row.findViewById(R.id.txtDuration) as TextView
        val durImg: ImageView = row.findViewById(R.id.timeImage) as ImageView
    }
}