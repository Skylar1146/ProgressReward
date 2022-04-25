package com.example.todoreward

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

//Used to add spacing between Recyclerview items
class SpacingItemDecoration : ItemDecoration() {

     var verticalSpaceHeight: Int = 10

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}