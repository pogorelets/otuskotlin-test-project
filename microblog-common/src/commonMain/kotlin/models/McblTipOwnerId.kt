package ru.otus.otuskotlin.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class McblTipOwnerId(private val ownerId: String) {
    fun asString() = ownerId

    companion object {
        val NONE = McblTipOwnerId("")
    }
}
