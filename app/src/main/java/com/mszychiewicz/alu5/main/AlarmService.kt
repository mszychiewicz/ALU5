package com.mszychiewicz.alu5.main

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import pl.daftacademy.androidlevelup.util.ext.logd
import java.util.*

private const val TAG = "AlarmService"

class AlarmService : IntentService("AlarmService") {

    private var alarmManager: AlarmManager? = null

    override fun onCreate() {
        super.onCreate()
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    override fun onHandleIntent(intent: Intent?) {
        "onHandleIntent()".logd(TAG)
        when (intent?.action) {
            ACTION_FAMILIADA -> {
                schedulePeriodicAlarm()
            }
        }
    }

    private fun schedulePeriodicAlarm() {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 16)
            set(Calendar.MINUTE, 20)
        }

        if (calendar.before(Calendar.getInstance().apply { timeInMillis = System.currentTimeMillis() })) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        Intent(this, MainBroadcastReceiver::class.java)
            .apply { action = ACTION_FAMILIADA }
            .let { PendingIntent.getBroadcast(this, 0, it, 0) }
            .let {
                alarmManager?.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    it
                )
            }
    }
}