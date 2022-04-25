package com.example.todoreward.ui.main

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.todoreward.*
import com.example.todoreward.pointHistory.ListPointHistoryFragment
import com.example.todoreward.reward.ListRewardFragment
import com.example.todoreward.task.ListTodoFragment


val TAB_TITLES = arrayOf(
    R.string.tabTodo,
    R.string.tabRewards,
    R.string.lblPoints
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {


    //return what fragment to show on change tab
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        return when (position) {
            0 -> ListTodoFragment.newInstance("", "")
            1 -> ListRewardFragment.newInstance("", "")
            else -> ListPointHistoryFragment.newInstance("", "")
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 2)
            ptAmount.toString();
        else
            context.resources.getString(TAB_TITLES[position])

    }

    override fun getCount(): Int {
        // Show X total pages.
        return 3
    }
}