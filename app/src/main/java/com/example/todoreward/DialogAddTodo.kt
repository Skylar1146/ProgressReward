package com.example.todoreward

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import java.lang.NumberFormatException
import java.util.*


class DialogAddTodo : DialogFragment(), DatePickerDialog.OnDateSetListener {
    companion object {
        const val TAG = "Dialog Add Task"
    }

    private val viewModel: TodoItemModel by activityViewModels()

    private lateinit var mContext: Context
    private lateinit var dateText: EditText

    public final val ARG_DATE_ARRAY = "date"
    public final val ARG_TASK_NAME = "name"
    public final val ARG_PTS_NAME = "points"
    public final val ARG_UID = "uid"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

    var setYear: Int = 0
    var setMonth: Int = 0
    var setDay: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add_task, container, false)
        val args = arguments


        var taskNameText = view.findViewById<TextView>(R.id.editTaskName)
        dateText = view.findViewById<EditText>(R.id.editTextDate)
        val ptText = view.findViewById<EditText>(R.id.editTextPts)
        var addBut = view.findViewById<Button>(R.id.butAddTaskDialog)


        var uID = getRandomString(8)
        var taskName = taskNameText?.text.toString()

        if (args != null) {
            addBut.text = "Confirm"
            if (args.containsKey(ARG_UID))
                uID = args.getString(ARG_UID).toString()
            if (args.containsKey(ARG_TASK_NAME)) {
                taskNameText.text = args.getString(ARG_TASK_NAME).toString()
            }
            if(args.containsKey(ARG_PTS_NAME))
                ptText.setText( args.getInt(ARG_PTS_NAME).toString())
        }
        addBut.setOnClickListener()
        {

            val todoItemData = ItemToDo.createToDoItem()

            todoItemData.itemDataText = taskNameText.text.toString()

            try {
                todoItemData.points = ptText.text.toString().toInt()
            }
            catch (nfe: NumberFormatException)
            {
                todoItemData.points = 0
            }


            todoItemData.done = false


            todoItemData.UID = uID

            //Set viewmodel to this, to be grabbed by FragToDoList
            viewModel.setItem(todoItemData)
            this.dismiss()
        }

        dateText.setOnClickListener()
        {
            val c = Calendar.getInstance()
            var datePickerDialog: DatePickerDialog = DatePickerDialog(
                mContext,
                this,
                c[Calendar.YEAR],
                c[Calendar.MONTH],
                c[Calendar.DAY_OF_MONTH]
            )
            //Min date is current date, have to subtract a second for ... some reason
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000;

            datePickerDialog.show()

        }
        if (args != null) {
            if (taskNameText != null && args.containsKey(ARG_TASK_NAME))
                taskNameText.text = args.getString(ARG_TASK_NAME)

            val dates = args.getIntArray(ARG_DATE_ARRAY)
            setDateText(dates!![0], dates[1], dates[2])

            ptText.setText(args.getInt(ARG_PTS_NAME).toString())
        }

        return view
    }

    private fun setDateText(year: Int, month: Int, day: Int) {
        if (dateText != null )
            dateText.setText("$month/$day/$year")

    }


    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        setYear = year
        setMonth = month
        setDay = day
        setDateText(year, month, day)
    }


}