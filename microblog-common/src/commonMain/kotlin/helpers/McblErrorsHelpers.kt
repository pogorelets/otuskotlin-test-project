package ru.otus.otuskotlin.microblog.common.helpers

import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblError
import ru.otus.otuskotlin.common.models.McblState


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

fun McblContext.fail(error: McblError) {
    addError(error)
    state = McblState.FAILING
}
fun errorValidation(
    field: String,
    violationCode: String,
    description: String,
) = McblError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
)


fun McblContext.addError(vararg error: McblError) = errors.addAll(error)
