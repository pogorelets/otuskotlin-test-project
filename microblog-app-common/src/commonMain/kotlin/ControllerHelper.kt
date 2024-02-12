package ru.otus.otuskotlin.microblog.app.common

import kotlinx.datetime.Clock
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.microblog.common.helpers.asMcblError

suspend inline fun <T> IMcblAppSettings.controllerHelper(
    crossinline getRequest: suspend McblContext.() -> Unit,
    crossinline toResponse: suspend McblContext.() -> T,
) : T {
    val ctx = McblContext(
        timeStart = Clock.System.now(),
    )
    return try {
        ctx.getRequest()
        processor.exec(ctx)
        ctx.toResponse()

    } catch (e: Throwable) {
        ctx.state = McblState.FAILING
        ctx.errors.add(e.asMcblError())
        processor.exec(ctx)
        ctx.toResponse()
    }
}