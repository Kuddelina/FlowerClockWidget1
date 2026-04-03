package com.flowerclock.widget

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper

/**
 * A lightweight Service that posts a Runnable every second to update
 * all Flower Clock widget instances. This gives smooth second-hand movement
 * without burning battery on a tight AlarmManager schedule.
 *
 * The service runs only while at least one widget is on the home screen
 * (started in onEnabled, stopped in onDisabled).
 */
class ClockTickService : Service() {

    private val handler = Handler(Looper.getMainLooper())
    private var running = false

    private val tickRunnable = object : Runnable {
        override fun run() {
            if (!running) return
            FlowerClockWidget.triggerUpdate(applicationContext)
            // Schedule next tick aligned to the next whole second
            val delay = 1000L - (System.currentTimeMillis() % 1000L)
            handler.postDelayed(this, delay)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!running) {
            running = true
            handler.post(tickRunnable)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        running = false
        handler.removeCallbacks(tickRunnable)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
