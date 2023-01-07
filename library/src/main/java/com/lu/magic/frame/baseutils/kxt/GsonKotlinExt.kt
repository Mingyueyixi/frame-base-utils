package com.lu.magic.frame.baseutils.kxt

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.lu.magic.util.GsonUtil


class JsonObjectKotlin {
    companion object {
        @JvmStatic
        fun from(jsonText: String): JsonObject {
            return GsonUtil.fromJson(jsonText, JsonObject::class.java)
        }
    }
}

class JsonArrayKotlin {
    companion object {
        @JvmStatic
        fun from(jsonText: String): JsonArray {
            return GsonUtil.fromJson(jsonText, JsonArray::class.java)
        }
    }
}

class JsonElementKotlin {
    companion object {
        @JvmStatic
        fun from(jsonText: String): JsonElement {
            return GsonUtil.fromJson(jsonText, JsonElement::class.java)
        }
    }
}

fun JsonObjectKotlin.from(jsonText: String): JsonObject {
    return GsonUtil.fromJson(jsonText, JsonObject::class.java)
}

fun JsonObject.from(jsonText: String): JsonObject {
    return GsonUtil.fromJson(jsonText, JsonObject::class.java)
}

fun JsonArray.from(jsonText: String): JsonArray {
    return GsonUtil.fromJson(jsonText, JsonArray::class.java)
}

fun JsonElement.from(jsonText: String): JsonElement? {
    return GsonUtil.fromJson(jsonText, JsonElement::class.java)
}

fun JsonObject.getString(memberName: String?): String {
    return this.get(memberName).asString
}

fun JsonObject.optString(memberName: String, fallback: String = ""): String {
    return try {
        getString(memberName)
    } catch (e: Exception) {
        null
    } ?: fallback
}


fun JsonObject.getBoolean(memberName: String): Boolean {
    return this.get(memberName).asBoolean
}

fun JsonObject.optBoolean(memberName: String, fallBack: Boolean = false): Boolean {
    return try {
        getBoolean(memberName)
    } catch (e: Exception) {
        null
    } ?: fallBack
}

fun JsonObject.getInt(memberName: String): Int {
    return this.get(memberName).asInt
}

fun JsonObject.optInt(memberName: String, fallback: Int = 0): Int {
    return try {
        getInt(memberName)
    } catch (e: Exception) {
        null
    } ?: fallback
}

fun JsonObject.getDouble(memberName: String): Double {
    return this.get(memberName).asDouble
}

fun JsonObject.optDouble(memberName: String, fallback: Double = Double.NaN): Double {
    return try {
        getDouble(memberName)
    } catch (e: Exception) {
        null
    } ?: fallback
}

fun JsonObject.getLong(memberName: String): Long {
    return this.get(memberName).asLong
}

fun JsonObject.optLong(memberName: String, fallback: Long = 0L): Long {
    return try {
        getLong(memberName)
    } catch (e: Exception) {
        null
    } ?: fallback
}

fun JsonObject.getJsonObject(memberName: String): JsonObject {
    return this.get(memberName).asJsonObject
}

fun JsonObject.optJsonObject(memberName: String): JsonObject? {
    return try {
        getJsonObject(memberName)
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.getJsonArray(memberName: String): JsonArray {
    return this.get(memberName).asJsonArray
}

fun JsonObject.optJsonArray(memberName: String): JsonArray? {
    return try {
        getJsonArray(memberName)
    } catch (e: Exception) {
        null
    }
}
