package com.example.todoreward

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragRewardList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: RewardItemModel by activityViewModels()
    private var rewardList: MutableList<RewardItem>? = null
    lateinit var adapter: RewardAdapter
    private lateinit var listViewItem: RecyclerView
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
            addReward(item)
        })
    }

    fun addReward(rewardItem: RewardItem)
    {
        rewardList?.add(rewardItem)
        adapter.notifyDataSetChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rewardList = mutableListOf<RewardItem>()

        val view: View = inflater.inflate(R.layout.fragment_frag__reward_list, container, false)
        listViewItem = view.findViewById(R.id.listViewRewards)
        //for showing items in list view
        adapter = RewardAdapter(mContext, rewardList!!)
        listViewItem.adapter = adapter

        var spacingItemDecorator: SpacingItemDecoration = SpacingItemDecoration()
        listViewItem.addItemDecoration(spacingItemDecorator)

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddReward)
        fab?.setOnClickListener {
            showAddRewardDialog(childFragmentManager)
        }

        populateRewardListExamples()
        // Inflate the layout for this fragment
        return view
    }

    private fun populateRewardListExamples()
    {
        val rewards = arrayOf("Watch a movie",  "Play video games - 30 mins","Play video games - 1 hr","Play Video games - 2 hrs","Get fast food", "Have a drink","Watch youtube - 30 mins", "Social Media - 30 mins", "Go Bowling",)
        val ptRewards = arrayOf(2,2,3,4,3,1,1,1,4)
        for (i in rewards.indices)
        {
            var newRwd = RewardItem.createRewardItem()
            newRwd.rewardName = rewards[i]
            newRwd.UID = i.toString()
            newRwd.pointCost = ptRewards[i]

            addReward(newRwd)
        }


    }

    private fun showAddRewardDialog(childFragmentManager: FragmentManager) {
        var dialog = DialogAddRwd()
        dialog.show(childFragmentManager, DialogAddRwd.TAG)
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