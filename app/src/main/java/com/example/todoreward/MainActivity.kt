package com.example.todoreward

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.todoreward.databinding.ActivityMainBinding
import com.example.todoreward.ui.main.SectionsPagerAdapter
import com.example.todoreward.ui.main.TAB_TITLES
import com.google.android.material.tabs.TabLayout


//Global Functions
public fun toast(text: String, context: Context) =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

public fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

public var ptAmount: Int = 0


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    fun updatePtTabTitle()
    {
        val tabLayout = binding.tabs
        val tab = tabLayout.getTabAt(2)
        if (tab != null) {
            tab.text = resources.getString(TAB_TITLES[2]) + ptAmount.toString();
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set up tabs
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}