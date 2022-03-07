package com.example.todoreward

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels


class DialogAddTodo : DialogFragment() {
    companion object {
        const val TAG = "Dialog Add Task"
    }

    private val viewModel: TodoItemModel by activityViewModels()


    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_add_task, container, false)

        var addBut = view.findViewById<Button>(R.id.butAddTaskDialog)
        addBut.setOnClickListener()
        {
            val todoItemData = ToDoItem.createToDoItem()

            var taskNameText =
                view.findViewById<TextView>(R.id.editTextRwdName)

            todoItemData.itemDataText = taskNameText?.text.toString()
            todoItemData.done = false

            todoItemData.UID = getRandomString(Int.SIZE_BITS - 1)

            //Set viewmodel to this, to be grabbed by FragToDoList
            viewModel.setItem(todoItemData)
            this.dismiss()

        }

        return view
    }


}