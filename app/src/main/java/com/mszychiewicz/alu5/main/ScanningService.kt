package com.mszychiewicz.alu5.main

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.mszychiewicz.alu5.R
import pl.daftacademy.androidlevelup.util.NotificationFactory
import pl.daftacademy.androidlevelup.util.ext.logd

private const val NOTIFICATION_ID_SCANNING = 4
private const val TAG = "ScanningService"

class ScanningService : Service() {

    private val notificationFactory by lazy { NotificationFactory() }

    override fun onCreate() {
        super.onCreate()
        "onCreate($this)".logd(TAG)
        Thread.currentThread().logd(TAG)
        startForeground(
            NOTIFICATION_ID_SCANNING,
            notificationFactory.create(
                this,
                getString(R.string.notification_4_title),
                getString(R.string.notification_4_body)
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy($this)".logd(TAG)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        "onStartCommand()".logd(TAG)
        Thread.currentThread().logd(TAG)
        showStartsToast()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        "onBind()".logd(TAG)
        Thread.currentThread().logd(TAG)
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        "onUnbind()".logd(TAG)
        return super.onUnbind(intent)
    }

    private fun showStartsToast() {
        Toast.makeText(this, getString(R.string.toast_scanning), Toast.LENGTH_SHORT).show()
    }
}