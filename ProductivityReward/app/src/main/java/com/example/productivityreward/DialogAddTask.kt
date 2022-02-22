package com.example.productivityreward

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class DialogAddTask(context: Context) : Dialog(context){

    init {
       // setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_task)

    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        dialog!!.window?.setBackgroundDrawableResource(R.drawable.custom);
////
////        var builder =  AlertDialog.Builder(activity)
////
////        var inflater  = activity?.layoutInflater
////        var view = inflater?.inflate(R.layout.dialog_add_task, null, false)
////        builder.setView(view)
//
//
//
//
//        return inflater?.inflate(R.layout.dialog_add_task, container, false)
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//    }
}