package com.flowerclock.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.util.Calendar

class FlowerClockWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
        // Start the tick service so the clock updates every second
        val serviceIntent = Intent(context, ClockTickService::class.java)
        context.startService(serviceIntent)
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        val serviceIntent = Intent(context, ClockTickService::class.java)
        context.startService(serviceIntent)
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        val serviceIntent = Intent(context, ClockTickService::class.java)
        context.stopService(serviceIntent)
    }

    companion object {
        /**
         * The hand images are square canvases with the needle pointing UP (12 o'clock = 0°).
         * The pivot point (the circle at the base of each hand) is at the center of the canvas.
         * Android's setRotation rotates around the view's own center, which matches perfectly.
         *
         * Rotation formula:
         *   Hour:   (hours % 12) * 30 + minutes * 0.5
         *   Minute: minutes * 6 + seconds * 0.1
         *   Second: seconds * 6
         */
        fun updateWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val now = Calendar.getInstance()
            val hours   = now.get(Calendar.HOUR)          // 0–11
            val minutes = now.get(Calendar.MINUTE)        // 0–59
            val seconds = now.get(Calendar.SECOND)        // 0–59

            val hourAngle   = hours * 30f + minutes * 0.5f
            val minuteAngle = minutes * 6f + seconds * 0.1f
            val secondAngle = seconds * 6f

            val views = RemoteViews(context.packageName, R.layout.flower_clock_widget)

            // Rotate each hand around its own center
            views.setFloat(R.id.hand_hour,   "setRotation", hourAngle)
            views.setFloat(R.id.hand_minute, "setRotation", minuteAngle)
            views.setFloat(R.id.hand_second, "setRotation", secondAngle)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun triggerUpdate(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(
                ComponentName(context, FlowerClockWidget::class.java)
            )
            for (id in ids) {
                updateWidget(context, manager, id)
            }
        }
    }
}
