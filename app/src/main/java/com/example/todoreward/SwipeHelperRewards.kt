package com.example.todoreward

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class SwipeHelperRewards(dragDirs: Int,
                                     swipeDirs: Int
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {


    var listener: RecyclerItemTouchHelperListener? = null

    open fun RecyclerItemTouchHelper(
        dragDirs: Int,
        swipeDirs: Int,
        listener: RecyclerItemTouchHelperListener?
    ) {
        this.listener = listener
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            val foregroundView: View = (viewHolder as RewardAdapter.RewardViewHolder).foreground
            getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder?,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View = (viewHolder as RewardAdapter.RewardViewHolder).foreground
        getDefaultUIUtil().onDrawOver(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val foregroundView: View = (viewHolder as RewardAdapter.RewardViewHolder).foreground
        getDefaultUIUtil().clearView(foregroundView)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val foregroundView: View = (viewHolder as RewardAdapter.RewardViewHolder).foreground
        getDefaultUIUtil().onDraw(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )

        if(dX >0) {
            onStartMovingRight(viewHolder)
        } else {
            onStartMovingLeft(viewHolder)
        }
    }

    open fun onStartMovingLeft(viewHolder: RecyclerView.ViewHolder)
    {
    }
    open fun onStartMovingRight(viewHolder: RecyclerView.ViewHolder)
    {
    }



    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener!!.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    interface RecyclerItemTouchHelperListener {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int)

    }
}