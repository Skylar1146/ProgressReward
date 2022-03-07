package com.example.todoreward

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragToDoListTest
{
    @Test
    fun testEventFragment() {
        launchFragmentInContainer<FragToDoList>()

        )