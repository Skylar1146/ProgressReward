package com.example.todoreward.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.todoreward.FragRewardList
import com.example.todoreward.FragToDoList
import com.example.todoreward.R
import com.example.todoreward.ptAmount

private val TAB_TITLES = arrayOf(
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
        return if (position == 0)
             FragToDoList.newInstance("", "")
         else
             FragRewardList.newInstance("", "")
        //todo: point page
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position == 2)
            context.resources.getString(TAB_TITLES[2]) + ptAmount.toString();
        else
            context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show X total pages.
        return 3
    }
}