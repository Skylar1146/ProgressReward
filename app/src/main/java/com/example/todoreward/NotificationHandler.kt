package com.example.todoreward

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import java.util.concurrent.TimeUnit


private const val NOTIF_CHANNEL_ID = "Chan_ID_Todo_01"
private const val NOTIF_ID = 101

fun createNotifChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Notif Title"
        val channel = NotificationChannel(
            NOTIF_CHANNEL_ID, name,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Desc Text"
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private var alarmMgr: AlarmManager? = null

fun scheduleNotification(context: Context) {
    alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager?
}


fun sendNotif(context: Context,title: String, message: String) {
    val intent = Intent(context, MainActivity::class.java).apply {
        //Opens as a new task on the history stack, or clear any existing
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    //Convert drawable to bitmap
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_pause_foreground)


    val builder = NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_check_foreground)
        .setStyle(NotificationCompat.InboxStyle())
        .setContentTitle(title)
        .setContentText(message)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)//Make disappear after tapping on it
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    with(NotificationManagerCompat.from(context))
    {
        notify(NOTIF_ID, builder.build())
    }

}
fun scheduleNotification(context: Context, message: String, timeDelayInSeconds: Long  ) {
    val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
        .setInitialDelay(timeDelayInSeconds, TimeUnit.SECONDS)
        .setInputData(workDataOf(
            "title" to "Upcoming Task Due Today",
            "message" to message,
        )
        )
        .build()

    WorkManager.getInstance(context).enqueue(myWorkRequest)
}
class ReminderWorker(val context: Context, val params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        sendNotif(
            context,
            inputData.getString("title").toString(),
            inputData.getString("message").toString()
        )

        return Result.success()
    }
}
