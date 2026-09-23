package com.invisibles.widgets

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.WindowInsets
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(28), dp(24), dp(24))
            setBackgroundColor(Color.WHITE)
        }

        page.addView(
            TextView(this).apply {
                text = getString(R.string.welcome_title)
                textSize = 28f
                setTextColor(Color.rgb(24, 29, 43))
                typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
            },
            matchWidthWrapHeight(),
        )

        page.addView(
            TextView(this).apply {
                text = getString(R.string.welcome_body)
                textSize = 16f
                setTextColor(Color.rgb(83, 91, 109))
                setLineSpacing(dp(4).toFloat(), 1f)
            },
            spacedHeight(dp(12)),
        )

        page.addView(
            Button(this).apply {
                text = getString(R.string.add_widget)
                isAllCaps = false
                setOnClickListener { requestWidgetPin() }
            },
            spacedHeight(dp(24)),
        )

        page.addView(sectionTitle(getString(R.string.steps_title)), spacedHeight(dp(28)))
        page.addView(instruction(getString(R.string.step_one)), spacedHeight(dp(10)))
        page.addView(instruction(getString(R.string.step_two)), spacedHeight(dp(10)))
        page.addView(instruction(getString(R.string.step_three)), spacedHeight(dp(10)))
        page.addView(instruction(getString(R.string.step_four)), spacedHeight(dp(10)))

        page.addView(sectionTitle(getString(R.string.home_page_title)), spacedHeight(dp(28)))
        page.addView(
            TextView(this).apply {
                text = getString(R.string.home_page_body)
                textSize = 15f
                setTextColor(Color.rgb(83, 91, 109))
                setLineSpacing(dp(4).toFloat(), 1f)
            },
            spacedHeight(dp(10)),
        )

        val scroll = ScrollView(this).apply {
            isFillViewport = true
            addView(page)
        }

        scroll.setOnApplyWindowInsetsListener { view, insets ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val bars = insets.getInsets(WindowInsets.Type.systemBars())
                view.setPadding(0, bars.top, 0, bars.bottom)
            } else {
                @Suppress("DEPRECATION")
                view.setPadding(0, insets.systemWindowInsetTop, 0, insets.systemWindowInsetBottom)
            }
            insets
        }
        setContentView(scroll)
    }

    private fun requestWidgetPin() {
        val manager = AppWidgetManager.getInstance(this)
        val provider = ComponentName(this, TransparentWidgetProvider::class.java)
        val requested = manager.isRequestPinAppWidgetSupported &&
            manager.requestPinAppWidget(provider, null, null)

        if (!requested) {
            Toast.makeText(this, R.string.pin_not_supported, Toast.LENGTH_LONG).show()
        }
    }

    private fun sectionTitle(text: String) = TextView(this).apply {
        this.text = text
        textSize = 19f
        setTextColor(Color.rgb(24, 29, 43))
        typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
    }

    private fun instruction(text: String) = TextView(this).apply {
        this.text = text
        textSize = 15f
        setTextColor(Color.rgb(59, 66, 82))
        gravity = Gravity.START
        setLineSpacing(dp(4).toFloat(), 1f)
    }

    private fun matchWidthWrapHeight() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
    )

    private fun spacedHeight(top: Int) = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
    ).apply { topMargin = top }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
