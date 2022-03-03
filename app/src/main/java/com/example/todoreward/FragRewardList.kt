package com.example.todoreward

import DialogAddRwd
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragRewardList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragRewardList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//      var tlBar =   (activity as AppCompatActivity?)!!.supportActionBar
//        tlBar?.setBackgroundDrawable( ColorDrawable(resources.getColor(R.color.rwdOrange)))


        val view: View = inflater.inflate(R.layout.fragment_frag__reward_list, container, false)
        //listViewItem = view.findViewById(R.id.listView)
        //for showing items in list view
        //adapter = ToDoAdapter(mContext, toDoList!!)
//        listViewItem.adapter = adapter

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddReward)
        fab?.setOnClickListener {
            showAddRewardDialog(childFragmentManager)

        }
        // Inflate the layout for this fragment
        return view
    }
    private fun showAddRewardDialog(childFragmentManager: FragmentManager) {
        var dialog = DialogAddRwd()
        dialog.show(childFragmentManager,DialogAddRwd.TAG)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Frag_RewardList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragRewardList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}