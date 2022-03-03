package com.example.todoreward

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider

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


     private var toDoList: MutableList<ToDoItem>? = null
     lateinit var adapter: ToDoAdapter
    private lateinit var listViewItem: ListView


    private val viewModel: TodoItemModel by activityViewModels()
   lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModel.selectedItem.observe(this, Observer { item ->
            // Perform an action with the latest item data
            toDoList?.add(item)
            adapter.notifyDataSetChanged()
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    toDoList = mutableListOf<ToDoItem>()
        val view: View = inflater.inflate(R.layout.fragment_frag__to_do_list, container, false)
        listViewItem = view.findViewById(R.id.listView)
        //for showing items in list view
        adapter = ToDoAdapter(mContext, toDoList!!)
        listViewItem.adapter = adapter



        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddTask)
        fab?.setOnClickListener {
            showAddTaskDialog(childFragmentManager)

        }
        // Inflate the layout for this fragment
        return view
    }

    private fun showAddTaskDialog(childFragmentManager:FragmentManager) {
        var dialog = DialogAddTodo()
        dialog.show(childFragmentManager,DialogAddTodo.TAG)
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
