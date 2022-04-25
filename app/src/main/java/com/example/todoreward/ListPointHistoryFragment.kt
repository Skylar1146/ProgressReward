package com.example.todoreward

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListPointHistoryFragment : Fragment()   {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private var listHistoryPoints: MutableList<ItemHistoryPoint>? = null
    lateinit var adapterPointHistory: AdapterPointHistory
    lateinit var mContext: Context

    //View models used to listen for completed/redeemed and add to history
    private val taskViewModel: TodoItemModel by activityViewModels()
    private val rewardViewModel: RewardItemModel by activityViewModels()



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

        taskViewModel.taskCompleted.observe(this, Observer { item ->
            // Perform an action with the latest item data
            addHistoryPoint(item)
        })
        rewardViewModel.redeemedReward.observe(this, Observer { item ->
            // Perform an action with the latest item data
            addHistoryPoint(item)
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_point_history, container, false)

        listHistoryPoints = mutableListOf<ItemHistoryPoint>()

        recyclerView = view.findViewById(R.id.listPtHistory)
        //for showing items in list view
        adapterPointHistory = AdapterPointHistory(listHistoryPoints!!)
        recyclerView.adapter = adapterPointHistory

        //Add spaces to recycler view items
        val spacingItemDecorator = SpacingItemDecoration()
        recyclerView.addItemDecoration(spacingItemDecorator)

        populatePointHistoryExamples()
        // Inflate the layout for this fragment
        return view
    }

    private fun populatePointHistoryExamples() {
        //todo: mark as reward or task
        val historyPoints = arrayOf(
            ItemHistoryPoint.createHistoryPointForTask("Wash car", 2, "4/22/2022"),
            ItemHistoryPoint.createHistoryPointForTask("Take out trash", 1, "9/05/2021")
        )
        for (i in historyPoints) {
            addHistoryPoint(i)
        }
    }
    private fun addHistoryPoint(toDo: ItemToDo) {
        val date = Calendar.getInstance()
        val dateStr = "${date.get(Calendar.MONTH)}/${date.get(Calendar.DAY_OF_MONTH)}/${date.get(Calendar.YEAR)}"
        val newHistoryPoint: ItemHistoryPoint =
            ItemHistoryPoint.createHistoryPointForTask(toDo.taskName,toDo.points,
                dateStr)
        addHistoryPoint(newHistoryPoint)
    }
    private fun addHistoryPoint(reward: ItemReward) {
        val date = Calendar.getInstance()
        val dateStr = "${date.get(Calendar.MONTH)}/${date.get(Calendar.DAY_OF_MONTH)}/${date.get(Calendar.YEAR)}"
        val newHistoryPoint: ItemHistoryPoint =
            ItemHistoryPoint.createHistoryPointForReward(reward.rewardName,reward.pointCost,
                dateStr)
        addHistoryPoint(newHistoryPoint)
    }



    private fun addHistoryPoint(historyPoint: ItemHistoryPoint) {
        listHistoryPoints?.add(historyPoint)
        adapterPointHistory.notifyDataSetChanged()
        toast("Add history point", mContext)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListPointHistory.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListPointHistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}