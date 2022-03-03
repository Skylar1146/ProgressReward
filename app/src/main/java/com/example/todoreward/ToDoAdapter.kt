package com.example.todoreward


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

/*
In charge of taking the list of to do items and applying them to the list view
 */
class ToDoAdapter(context: Context, todolist: MutableList<ToDoItem>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = todolist
    // private lateinit var updateAndDelete: UpdateAndDelete


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val viewHolder: ListViewHolder

        if (p1 == null) {
            view = inflater.inflate(R.layout.row_item_todo, p2, false)
            viewHolder = ListViewHolder(view)
            view.tag = viewHolder
        } else {
            view = p1
            viewHolder = view.tag as ListViewHolder
        }

        if (itemList[p0] != null) {
            val UID: String = itemList[p0].UID as String
            val itemTextData = itemList[p0].itemDataText as String
            val done: Boolean = itemList[p0].done as Boolean




            viewHolder.textLabel.text = itemTextData
            viewHolder.isDone.isChecked = done

        }

//        viewHolder.isDone.setOnClickListener {
//            updateAndDelete.setIsDoneState(UID, !done)
//        }
//        viewHolder.isDeleted.setOnClickListener {
//            updateAndDelete.onItemDelete(UID)
//        }

        return view
    }

    private class ListViewHolder(row: View?) {
        val textLabel: TextView = row!!.findViewById(R.id.item_text) as TextView
        val isDone: CheckBox = row!!.findViewById(R.id.checkbox) as CheckBox
        val isDeleted: ImageButton = row!!.findViewById(R.id.close) as ImageButton
    }

    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }
}