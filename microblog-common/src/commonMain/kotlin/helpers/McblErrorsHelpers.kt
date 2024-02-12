package ru.otus.otuskotlin.microblog.common.helpers

import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblError


fun Throwable.asMcblError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = McblError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun McblContext.addError(vararg error: McblError) = errors.addAll(error)
