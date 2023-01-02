package com.lu.magic.util

import android.content.Context
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
}
