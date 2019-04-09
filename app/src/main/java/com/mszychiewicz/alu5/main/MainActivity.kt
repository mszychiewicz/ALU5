package com.mszychiewicz.alu5.main

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.work.*
import com.mszychiewicz.alu5.R
import kotlinx.android.synthetic.main.activity_main.*

const val ACTION_FAMILIADA = "com.mszychiewicz.ALU5.NOTIFY_ALARM_FAMILIADA"

class MainActivity : AppCompatActivity() {

    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        scheduleSingleWork()

        startService(Intent(this, AlarmService::class.java).apply { action = ACTION_FAMILIADA })

        ContextCompat.startForegroundService(this, Intent(this, ScanningService::class.java))

        buttonStartScanning.setOnClickListener {
            ContextCompat.startForegroundService(this, Intent(this, ScanningService::class.java))
        }
        buttonStopScanning.setOnClickListener {
            stopService(Intent(this, ScanningService::class.java))
        }
    }

    private fun scheduleSingleWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
        val request = OneTimeWorkRequest.Builder(SendNotificationWorker::class.java)
            .setConstraints(constraints)
            .build()
        WorkManager
            .getInstance()
            .enqueueUniqueWork(SendNotificationWorker::javaClass.name, ExistingWorkPolicy.APPEND, request)
    }
}