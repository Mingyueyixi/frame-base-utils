package com.lu.magic.util.log

import android.util.Log

open class SimpleLogger : ILogger {
    companion object {
        const val TAG = ">>>"
        const val LEVEL_V = 0
        const val LEVEL_D = 1
        const val LEVEL_I = 2
        const val LEVEL_W = 3
        const val LEVEL_E = 4
    }

    override fun v(vararg objects: Any?) {
        onLog(0, objects)
    }

    override fun d(vararg objects: Any?) {
        onLog(1, objects)
    }

    override fun i(vararg objects: Any?) {
        onLog(2, objects)
    }

    override fun w(vararg objects: Any?) {
        onLog(3, objects)
    }

    override fun e(vararg objects: Any?) {
        onLog(4, objects)
    }

    open fun beforeLog(level: Int, objects: Array<out Any?>, msg: String) {

    }

    open fun onLog(level: Int, objects: Array<out Any?>) {
        val msg = buildLogText(objects)
        beforeLog(level, objects, msg)
        when (level) {
            LEVEL_V -> Log.v(TAG, msg)
            LEVEL_D -> Log.d(TAG, msg)
            LEVEL_I -> Log.i(TAG, msg)
            LEVEL_W -> Log.w(TAG, msg)
            LEVEL_E -> Log.e(TAG, msg)
            else -> Log.d(TAG, msg)
        }
        afterLog(level, objects, msg)
    }

    open fun afterLog(level: Int, objects: Array<out Any?>, msg: String) {

    }

    open fun buildLogText(objects: Array<out Any?>): String {
        val text = StringBuffer()
        objects.forEachIndexed { i, obj ->
            if (i != 0) {
                text.append("  ")
            }
            if (obj == null) {
                text.append("null")
            } else {
                if (obj is Throwable) {
                    text.append(Log.getStackTraceString(obj))
                } else if (obj.javaClass.isArray) {
                    text.append((obj as Array<*>).contentToString())
                } else {
                    text.append(obj)
                }
            }
        }
        return text.toString()
    }

}