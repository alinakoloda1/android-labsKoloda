package com.example.laba8

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews

class NewAppWidgetConfigureActivity : Activity() {

    private var context: NewAppWidgetConfigureActivity? = null
    private var widgetID = 0

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.new_app_widget_configure)
        setResult(RESULT_CANCELED)
        context = this

        val extras = intent.extras

        if (extras != null) {
            widgetID = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )

            val widgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context!!.packageName, R.layout.new_app_widget)
            val editText = findViewById<View>(R.id.appwidget_text) as EditText
            val button = findViewById<View>(R.id.add_button) as Button

            button.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(editText.text.toString()))
                val pending = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.appwidget_text, pending)
                widgetManager.updateAppWidget(widgetID, views)

                val resultValue = Intent()
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
                setResult(RESULT_OK, resultValue)
                finish()
            }
        }
    }
}