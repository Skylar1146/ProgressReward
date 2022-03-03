package com.example.todoreward.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.todoreward.FragRewardList
import com.example.todoreward.FragToDoList
import com.example.todoreward.R

private val TAB_TITLES = arrayOf(
    R.string.tabTodo,
    R.string.tabRewards
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


        return if (position == 0)
             FragToDoList.newInstance("", "")
         else
             FragRewardList.newInstance("", "")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}