package com.mszychiewicz.alu5

import android.app.Application
import android.os.Build
import pl.daftacademy.androidlevelup.util.NotificationFactory
import pl.daftacademy.androidlevelup.util.ext.fromAndroid

class ALU5 : Application() {

    private val notificationFactory by lazy { NotificationFactory() }

    override fun onCreate() {
        super.onCreate()
        initNotificationChannels()
    }

    private fun initNotificationChannels() {
        fromAndroid(Build.VERSION_CODES.O) { notificationFactory.createNotificationChannels(this) }
    }
}