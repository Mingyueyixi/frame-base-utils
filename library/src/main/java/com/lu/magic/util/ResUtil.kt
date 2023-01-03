package com.lu.magic.util

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.ColorInt


object ResUtil {
    @JvmStatic
    @ColorInt
    fun getAttrColor(context: Context, resId: Int): Int {
        val typedValue = TypedValue()
        val theme: Resources.Theme = context.theme
        theme.resolveAttribute(resId, typedValue, true)
        return typedValue.data
    }

    fun isAppNightMode(context: Context): Boolean {
        val uiMode = context.resources.configuration.uiMode
        return (uiMode and  Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }

    fun isSystemNightMode(context: Context) :Boolean{
        val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        return uiModeManager.nightMode == UiModeManager.MODE_NIGHT_YES
    }
}
