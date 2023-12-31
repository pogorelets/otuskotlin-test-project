package ru.otus.otuskotlin.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class McblTipId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = McblTipId("")
    }
}
