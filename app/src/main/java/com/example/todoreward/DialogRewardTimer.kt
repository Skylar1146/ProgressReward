package com.example.todoreward

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.lang.String
import java.util.concurrent.TimeUnit


class DialogRewardTimer : DialogFragment() {

    private final val hoursArgName = "HOURS"
    private final val minsArgName = "MINS"
    var hours: Long = 0
    var mins: Long = 0
    private lateinit var leftBut: Button
    /*
        Button stages

        NotBegun = Start | Exit
        InProgress = Pause | Stop
        Paused = Resume | Exit
         */
    enum class TimerStage {
        NotBegun,
        InProgress,
        Paused
    }

    companion object {
        @JvmStatic

        fun newInstance(hours: Long, mins: Long) = DialogRewardTimer().apply {
            arguments = Bundle().apply {
                putLong(hoursArgName, hours)
                putLong(minsArgName, mins)
            }
        }

    }

    var _timerStage : TimerStage = TimerStage.NotBegun
    var timerStage: TimerStage
        get() = _timerStage
        set(value) {
            //change left button image
            _timerStage = value
            if (timerStage == TimerStage.InProgress)
                leftBut.foreground = resources.getDrawable(R.drawable.ic_pause_foreground)
            else
                leftBut.foreground = resources.getDrawable(R.drawable.ic_resume_foreground)
        }


    var milliLeft: Long = 0

    private fun clickLeftButton() {

        if (timerStage == TimerStage.NotBegun) {
            val millisecondsToStartAt = getHoursAndMinsAsMilliseconds(hours, mins)
            timerStart(millisecondsToStartAt)

            timerStage = TimerStage.InProgress
        }
        //Timers cannot be paused/resumed natively. Must stop and recreate at last time
        else if (timerStage == TimerStage.InProgress)//Pause
        {
            timer.cancel()
            timerStage = TimerStage.Paused
        } else {
            timerStart(milliLeft)
            timerStage = TimerStage.InProgress
        }


    }

    private fun getHoursAndMinsAsMilliseconds(hours: Long, mins: Long): Long {
        return hours * 360000 + mins * 60000
    }


    private fun clickStopButton() {

        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        this.dismiss()//close timer dialog
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to end this reward early?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }


    lateinit var timer: CountDownTimer

    lateinit var timerText: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_timer_reward, container, false)

        this.hours = arguments?.get(hoursArgName) as Long
        this.mins = arguments?.get(minsArgName) as Long

        timerText = view.findViewById<TextView>(R.id.txtTimer)
        setTextToShowMilliAsHoursMinsSecs(getHoursAndMinsAsMilliseconds(hours, mins))
        var titleText = view.findViewById<TextView>(R.id.dlgTimerRewardName)
        // titleText.text = todo: set title
        leftBut = view.findViewById<Button>(R.id.btnLeft);
        leftBut.setOnClickListener()
        {
            clickLeftButton()
        }

        var rightBut = view.findViewById<Button>(R.id.btnStop);
        rightBut.setOnClickListener()
        {
            clickStopButton()
        }
        var addBut = view.findViewById<Button>(R.id.butAddReward)

        return view
    }

    fun setTextToShowMilliAsHoursMinsSecs(millis: Long) {
        val hoursLeft: Long = millis / 360000;
        val minsLeft: Long = millis / 60000;

        val hours = TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(
            TimeUnit.MILLISECONDS.toDays(millis)
        )
        val mins = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(millis)
        )
        val secs = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millis)
        )
        timerText.text = String.format(
            "$hours hrs,$mins mins $secs seconds",
        )
        //todo: don't show hours, mins if none


    }

    private fun timerStart(milliseconds: Long) {
        timer = object : CountDownTimer(milliseconds, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millis: Long) {
                milliLeft = millis
                setTextToShowMilliAsHoursMinsSecs(millis)
            }

            override fun onFinish() {
                //add your code here
            }

        }
        timer.start()


    }
}