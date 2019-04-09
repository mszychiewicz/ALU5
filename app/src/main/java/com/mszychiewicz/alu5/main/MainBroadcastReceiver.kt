package com.mszychiewicz.alu5.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.mszychiewicz.alu5.R
import pl.daftacademy.androidlevelup.util.NotificationFactory
import pl.daftacademy.androidlevelup.util.ext.logd

const val NOTIFICATION_ID_BOOT_COMPLETED = 1
const val NOTIFICATION_ID_FAMILIADA = 2

private const val TAG = "MainBroadcastReceiver"

class MainBroadcastReceiver : BroadcastReceiver() {


    private val notificationFactory by lazy { NotificationFactory() }

    override fun onReceive(context: Context, intent: Intent) {
        "Action: ${intent.action}".logd(TAG)
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                with(NotificationManagerCompat.from(context)) {
                    notify(
                        NOTIFICATION_ID_BOOT_COMPLETED, notificationFactory.create(
                            context,
                            context.getString(R.string.notification_1_title),
                            context.getString(R.string.notification_1_body)
                        )
                    )
                }
            }
            ACTION_FAMILIADA -> {
                with(NotificationManagerCompat.from(context)) {
                    notify(
                        NOTIFICATION_ID_FAMILIADA, notificationFactory.create(
                            context,
                            context.getString(R.string.notification_2_title),
                            context.getString(R.string.notification_2_body)
                        )
                    )
                }
            }
        }
    }
}