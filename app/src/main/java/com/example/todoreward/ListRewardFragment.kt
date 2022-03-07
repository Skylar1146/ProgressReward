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


class ListRewardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: RewardItemModel by activityViewModels()
    private var listReward: MutableList<ItemReward>? = null
    lateinit var adapterReward: AdapterReward
    private lateinit var recyclerView: RecyclerView
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

        viewModel.selectedItemReward.observe(this, Observer { item ->
            // Perform an action with the latest item data
            addReward(item)
        })
    }

    fun addReward(itemReward: ItemReward)
    {
        listReward?.add(itemReward)
        adapterReward.notifyDataSetChanged()
    }
    fun setDeleteVisible(value: Boolean, deleteBG: RelativeLayout, completeBG: RelativeLayout) {
        deleteBG.isVisible = value
        completeBG.isVisible = !value
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.listViewRewards)
        //for showing items in list view
        adapterReward = AdapterReward(listReward!!)
        recyclerView.adapter = adapterReward

        //Add spaces to recycler view items
        var spacingItemDecorator: SpacingItemDecoration = SpacingItemDecoration()
        recyclerView.addItemDecoration(spacingItemDecorator)


        val swipeToDeleteCallback =
            object : SwipeHelperRewards(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val pos = viewHolder.layoutPosition

                    //if swipe right, collect reward
                    if (direction == ItemTouchHelper.RIGHT) {

                        var rewardItem: ItemReward = listReward!![pos]
                        if(rewardItem.canAfford())//Collect task
                        {
                            ptAmount -= rewardItem.pointCost
                            (activity as MainActivity).updatePtTabTitle()//update tab to show point change
                            toast("Collected Reward '" + rewardItem.rewardName + "'", mContext)
                            listReward!!.removeAt(pos)
                            adapterReward.notifyItemRemoved(pos)
                        }
                        else {
                            toast(
                                "You need ${rewardItem.pointCost - ptAmount} more points for this reward",
                                mContext
                            )
                            //Calling this will undo swipe
                            adapterReward.notifyItemChanged(pos)
                        }
                    }
                    else {//Delete
                        listReward!!.removeAt(pos)
                        adapterReward.notifyItemRemoved(pos)
                    }
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
        listReward = mutableListOf<ItemReward>()

        val view: View = inflater.inflate(R.layout.fragment_frag__reward_list, container, false)

        setupRecyclerView(view)

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
            var newRwd = ItemReward.createRewardItem()
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
            ListRewardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}