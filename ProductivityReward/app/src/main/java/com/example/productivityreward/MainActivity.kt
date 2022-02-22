package com.example.productivityreward

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), UpdateAndDelete {


    /*todo
    -show points icon on both screens
    -notifications for todo items
        -click on todo item to edit values
    -

    -settings page




    -Tap for rewards
    -Add reward
    -reward timer
    -tap on reward to edit values
     */
    private var toDoList: MutableList<ToDoItem>? = null
    private lateinit var adapter: ToDoAdapter
    private var listViewItem: ListView? = null

    private var ptEditText: EditText? = null

    private  var points:Int = 0
        get() = field;
        set(value) {
            field = value
            findViewById<EditText>(R.id.txtPtAmo).setText( points.toString())
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<View>(R.id.butAddTask) as FloatingActionButton
        ptEditText = findViewById<EditText>(R.id.txtPtAmo)
        points = 0//Sets the text to 0//todo: reload saved
        toDoList = mutableListOf<ToDoItem>()

        fab.setOnClickListener {
            showAddTaskDialog()
        }

        //for showing items in list view
        adapter = ToDoAdapter(this, toDoList!!)

        listViewItem = findViewById(R.id.listView)
        listViewItem!!.adapter = adapter

        // items =
    }


    private fun showAddTaskDialog()
    {
        var dialog = Dialog(this)


        //        var dialog = DialogAddTask(this)
        dialog.setContentView(R.layout.dialog_add_task)

        var addBut = dialog.findViewById<Button>(R.id.butAddTaskDialog)

        addBut.setOnClickListener()
        {
            val todoItemData = ToDoItem.createToDoItem()

            var taskNameText =
                dialog.findViewById<TextView>(R.id.editTextTextPersonName)

            todoItemData.itemDataText = taskNameText?.text.toString()
            todoItemData.done = false

            var ptText =  dialog.findViewById<EditText>(R.id.editTextPts)
            if(ptText != null)
            todoItemData.points = ptText.text.toString().toInt()


            todoItemData.UID = getRandomString(Int.SIZE_BITS - 1)

            toDoList?.add((todoItemData))
            adapter.notifyDataSetChanged()

            toast("Task has been added!")
            dialog.dismiss()

        }
        dialog.show()
    }


    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

//    private fun addItemToList(snapshot: DataSnapshot) {
//        val items = snapshot.children.iterator()
//
//        if (items.hasNext()) {
//            val toDoIndexValue = items.next()
//            val itemsIterator = toDoIndexValue.children.iterator()
//
//            while (itemsIterator.hasNext()) {
//                val currentItem = itemsIterator.next()
//                val toDoItemData = ToDoItem.createToDoItem()
//                val map = currentItem.value as HashMap<String, Any>
//
//                toDoItemData.UID = currentItem.key
//                toDoItemData.done = map.get("done") as Boolean?
//                toDoItemData.itemDataText = map.get("itemDataText") as String?
//
//                toDoList!!.add(toDoItemData)
//            }
//        }
//
//    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun getItemByUID(itemUID: String): ToDoItem? {
        var itemFound: ToDoItem? = null
        for (item in toDoList!!) {
            if (item.UID == itemUID) {
                itemFound = item
                break
            }
        }
        return itemFound
    }


    override fun setIsDoneState(itemUID: String, isDone: Boolean) {
        var item =  getItemByUID(itemUID)
        //add points
        if(item != null) {
            item.done = isDone

            if(isDone)
                points += item.points
            else
                points -= item.points
        }

    }

    override fun onItemDelete(itemUID: String) {
        val foundItem = getItemByUID(itemUID)
        if (foundItem != null)
            toDoList?.remove(foundItem)
        adapter.notifyDataSetChanged()
    }
}