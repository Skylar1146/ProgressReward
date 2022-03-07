package com.example.todoreward

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragToDoList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragToDoList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var todoItems: MutableList<ToDoItem>? = null
    lateinit var adapter: ToDoAdapter
    private lateinit var recyclerView: RecyclerView


    private val viewModel: TodoItemModel by activityViewModels()
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    private fun populateTodoListExamples() {
        val todoItems = arrayOf(
            "Take Out Trash",
            "Oil Change",
            "Schedule Dentist Appointment",
            "Wash Car",
            "Finish test 2",
            "Install new shelves"
        )
        val ptRewards = arrayOf(1, 2, 1, 2, 4, 6)
        for (i in todoItems.indices) {
            var newTodoItem = ToDoItem.createToDoItem()
            newTodoItem.itemDataText = todoItems[i]
            newTodoItem.UID = i.toString()
            newTodoItem.points = ptRewards[i]

            addToDoItem(newTodoItem)
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModel.selectedItem.observe(this, Observer { item ->
            // Perform an action with the latest item data
            addToDoItem(item)
        })

    }

    fun addToDoItem(item: ToDoItem) {
        todoItems?.add(item)
        adapter.notifyDataSetChanged()
    }


    fun setDeleteVisible(value: Boolean, deleteBG: RelativeLayout, completeBG: RelativeLayout) {
        deleteBG.isVisible = value
        completeBG.isVisible = !value
    }


    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.listView)
        //for showing items in list view
        adapter = ToDoAdapter(mContext, todoItems!!)
        recyclerView.adapter = adapter

        //Add spaces to recycler view items
        var spacingItemDecorator: SpacingItemDecoration = SpacingItemDecoration()
        recyclerView.addItemDecoration(spacingItemDecorator)


        val swipeToDeleteCallback =
            object : SwipeToDeleteCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    val pos = viewHolder.adapterPosition

                    //if swipe right, add pts
                    if (direction == ItemTouchHelper.RIGHT) {
                        ptAmount += todoItems!![pos].points
                        (activity as MainActivity).updatePtTabTitle()//update tab to show point change
                        toast("Completed task '" + todoItems!![pos].itemDataText + "'", mContext)
                    }

                    todoItems!!.removeAt(pos)
                    adapter.notifyItemRemoved(pos)
                }
                //Set what background to show (red delete or green complete) on swipe
                override fun onStartMovingLeft(viewHolder: RecyclerView.ViewHolder) {
                    val bgDelete = viewHolder.itemView.findViewById<RelativeLayout>(R.id.background)
                    val bgComplete =
                        viewHolder.itemView.findViewById<RelativeLayout>(R.id.backgroundComplete)
                    setDeleteVisible(true, bgDelete, bgComplete)
                }

                override fun onStartMovingRight(viewHolder: RecyclerView.ViewHolder) {
                    val bgDelete = viewHolder.itemView.findViewById<RelativeLayout>(R.id.background)
                    val bgComplete =
                        viewHolder.itemView.findViewById<RelativeLayout>(R.id.backgroundComplete)
                    setDeleteVisible(false, bgDelete, bgComplete)
                }

            }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todoItems = mutableListOf<ToDoItem>()
        val view: View = inflater.inflate(R.layout.fragment_frag__to_do_list, container, false)

        setupRecyclerView(view)

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddTask)
        fab?.setOnClickListener {
            showAddTaskDialog(childFragmentManager)
        }

        populateTodoListExamples()

        // Inflate the layout for this fragment
        return view
    }

    private fun showAddTaskDialog(childFragmentManager: FragmentManager) {
        var dialog = DialogAddTodo()
        dialog.show(childFragmentManager, DialogAddTodo.TAG)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragTodoList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragToDoList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

//    override fun onToDoAdded(item: ToDoItem) {
//        toast("added",mContext)
//    }
}
