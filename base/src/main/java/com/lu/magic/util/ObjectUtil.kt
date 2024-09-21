package com.lu.magic.util

import com.google.gson.internal.UnsafeAllocator
import com.lu.magic.util.function.Consumer

class ObjectUtil {
    companion object {
        @JvmStatic
        fun <T> factory(clazz: Class<T>): WithFactory<T> {
            return WithFactory(clazz)
        }
    }

    class WithFactory<T>(val clazz: Class<T>) : Factory<T> {
        private var onUnsafeFailBlock: Consumer<Throwable>? = null
        private var onNewInstanceFailBlock: Consumer<Throwable>? = null
        fun onNewInstanceFail(block: Consumer<Throwable>): WithFactory<T> {
            onNewInstanceFailBlock = block
            return this;
        }

        fun onUnsafeFail(block: Consumer<Throwable>): WithFactory<T> {
            onUnsafeFailBlock = block
            return this
        }

        override fun create(): T? {
            var result: T? = null
            result = runCatching {
                clazz.newInstance()
            }.getOrElse {
                onNewInstanceFailBlock?.accept(it)
                null
            }
            if (result != null) {
                return result
            }
            result = runCatching {
                UnsafeAllocator.INSTANCE.newInstance(clazz)
            }.getOrElse {
                onUnsafeFailBlock?.accept(it)
                null
            }
            return result

        }

    }

    interface Factory<T> {
        fun create(): T?

    }
}