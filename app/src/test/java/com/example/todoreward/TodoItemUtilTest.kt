package com.example.todoreward

import com.example.todoreward.task.ItemToDo
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

class TodoItemUtilTest {

    //region todo-item name validation
    @Test
    fun `empty todo-item name returns false`()
    {
        val result = ItemToDo.nameIsValid("")
        assertThat(result).isFalse()
    }
    @Test
    fun `1 character long todo-item name returns false`()
    {
        val result = ItemToDo.nameIsValid("A")
        assertThat(result).isFalse()
    }
    @Test
    fun `2 character long todo-item name returns false`()
    {
        val result = ItemToDo.nameIsValid("AB")
        assertThat(result).isFalse()
    }
    @Test
    fun `3 character long todo-item name returns true`()
    {
        val result = ItemToDo.nameIsValid("ABC")
        assertThat(result).isTrue()
    }
    @Test
    fun `valid todo-item name returns true`()
    {
        val result = ItemToDo.nameIsValid("Do the laundry")
        assertThat(result).isTrue()
    }
    //endregion

    //region todo-item point validation
    @Test
    fun `0 point todo-item returns false`()
    {
        val result = ItemToDo.pointValueIsValid(0)
        assertThat(result).isFalse()
    }
    @Test
    fun `1 point todo-item returns true`()
    {
        val result = ItemToDo.pointValueIsValid(1)
        assertThat(result).isTrue()
    }

    @Test
    fun `100 point todo-item returns true`()
    {
        val result = ItemToDo.pointValueIsValid(100)
        assertThat(result).isTrue()
    }
    @Test
    fun `101 point todo-item returns false`()
    {
        val result = ItemToDo.pointValueIsValid(101)
        assertThat(result).isFalse()
    }
    //endregion

    //region date
    @Test
    fun `Date 1 year ago returns false`()
    {
        var newDate = Calendar.getInstance()
        newDate.set(Calendar.YEAR,newDate.get(Calendar.YEAR)-1)
        val result = ItemToDo.dateIsValid(newDate)
        assertThat(result).isFalse()
    }
    //region date
    @Test
    fun `Date 1 year ahead returns true`()
    {
        var newDate = Calendar.getInstance()
        newDate.set(Calendar.YEAR,newDate.get(Calendar.YEAR)+1)
        val result = ItemToDo.dateIsValid(newDate)
        assertThat(result).isTrue()
    }


    //endregion
}
