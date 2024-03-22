package com.lu.magic.util

import android.database.Cursor
import androidx.core.database.getBlobOrNull
import androidx.core.database.getDoubleOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull

object CursorUtil {

    @JvmOverloads
    fun getAll(
        cursor: Cursor?,
        formPrevious: Boolean = true,
        revertPos: Boolean = false
    ): MutableList<MutableMap<String, Any?>> {
        val lst = mutableListOf<MutableMap<String, Any?>>()
        if (cursor == null) {
            return lst
        }
        val pos = cursor.position
        if (formPrevious) {
            cursor.moveToPrevious()
        }
        while (cursor.moveToNext()) {
            val row = mutableMapOf<String, Any?>()
            for (columnName in cursor.columnNames) {
                val columnIndex = cursor.getColumnIndex(columnName)
                val value = when (cursor.getType(columnIndex)) {
                    Cursor.FIELD_TYPE_BLOB -> cursor.getBlobOrNull(columnIndex)
                    Cursor.FIELD_TYPE_FLOAT -> cursor.getDoubleOrNull(columnIndex)
                    Cursor.FIELD_TYPE_STRING -> cursor.getStringOrNull(columnIndex)
                    Cursor.FIELD_TYPE_INTEGER -> cursor.getLongOrNull(columnIndex)
                    Cursor.FIELD_TYPE_NULL -> null
                    else -> null
                }
                row[columnName] = value
            }
            lst.add(row)
        }
        if (revertPos) {
            cursor.moveToPosition(pos)
        }
        return lst
    }

    fun <T> getAll(cursor: Cursor?, itemType: Class<T>): MutableList<T> {
        if (cursor == null) {
            return arrayListOf()
        }
        val lst = getAll(cursor)
        return runCatching {
            val json = GsonUtil.toJson(lst)
            GsonUtil.fromJson<MutableList<T>>(
                json,
                GsonUtil.getType(MutableList::class.java, itemType)
            )
        }.getOrDefault(mutableListOf())

    }

}