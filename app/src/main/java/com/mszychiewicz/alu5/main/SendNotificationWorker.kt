package com.mszychiewicz.alu5.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mszychiewicz.alu5.R
import pl.daftacademy.androidlevelup.util.NotificationFactory
import pl.daftacademy.androidlevelup.util.ext.logd


const val NOTIFICATION_ID_CHARGING = 3
private const val TAG = "SendNotificationWorker"

class SendNotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val notificationFactory by lazy { NotificationFactory() }

    override fun doWork(): Result {
        "doWork()".logd(TAG)
        Handler(Looper.getMainLooper()).post {
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(
                    NOTIFICATION_ID_CHARGING, notificationFactory.create(
                        applicationContext,
                        applicationContext.getString(R.string.notification_3_title),
                        applicationContext.getString(R.string.notification_3_body)
                    )
                )
            }
        }
        return Result.success()
    }
}