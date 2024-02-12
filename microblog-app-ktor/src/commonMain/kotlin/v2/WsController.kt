package ru.otus.otuskotlin.app.ktor.v2

import McblAppSettings
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import ru.otus.otuskotlin.api.v2.apiV2RequestDeserialize
import ru.otus.otuskotlin.api.v2.apiV2ResponseSerialize
import ru.otus.otuskotlin.api.v2.models.IRequest
import ru.otus.otuskotlin.app.ktor.base.KtorWsSessionV2
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.mappers.v2.fromTransport
import ru.otus.otuskotlin.mappers.v2.toTransportInit
import ru.otus.otuskotlin.mappers.v2.toTransportTip
import ru.otus.otuskotlin.microblog.app.common.controllerHelper

suspend fun WebSocketSession.wsHandlerV2(appSettings: McblAppSettings) = with(
    KtorWsSessionV2(this)
) {
    val sessions = appSettings.corSettings.wsSessions
    sessions.add(this)

    appSettings.controllerHelper(
        { command = McblCommand.INIT },
        { outgoing.send(Frame.Text(apiV2ResponseSerialize(toTransportInit()))) },
    )


    incoming.receiveAsFlow().mapNotNull { it ->
        val frame = it as? Frame.Text ?: return@mapNotNull
        try {
            appSettings.controllerHelper(
                { fromTransport(apiV2RequestDeserialize<IRequest>(frame.readText())) },
                {
                    val result = apiV2ResponseSerialize(toTransportTip())
                    outgoing.send(Frame.Text(result))
                },
            )

        } catch (_: ClosedReceiveChannelException) {
            sessions.clearAll()
        } catch (e: Throwable) {
            println("Error")
        }

        appSettings.controllerHelper(
            { command = McblCommand.FINISH },
            { },
        )
    }.collect()
}
