package ru.otus.otuskotlin.app.ktor.v1

import McblAppSettings
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import ru.otus.otuskotlin.api.v1.apiV1Mapper
import ru.otus.otuskotlin.api.v1.models.IRequest
import ru.otus.otuskotlin.app.ktor.base.KtorWsSessionV1
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.mappers.v1.fromTransport
import ru.otus.otuskotlin.mappers.v1.toTransportInit
import ru.otus.otuskotlin.mappers.v1.toTransportTip
import ru.otus.otuskotlin.microblog.app.common.controllerHelper

suspend fun WebSocketSession.wsHandlerV1(appSettings: McblAppSettings) = with(
    KtorWsSessionV1(this)
) {
    val sessions = appSettings.corSettings.wsSessions
    sessions.add(this)

    appSettings.controllerHelper(
        { command = McblCommand.INIT },
        { outgoing.send(Frame.Text(apiV1Mapper.writeValueAsString(toTransportInit()))) },
    )

    incoming.receiveAsFlow().mapNotNull { it ->
        val frame = it as? Frame.Text ?: return@mapNotNull
        try {
            appSettings.controllerHelper(
                { fromTransport(apiV1Mapper.readValue<IRequest>(frame.readText())) },
                {
                    val result = apiV1Mapper.writeValueAsString(toTransportTip())
                    outgoing.send(Frame.Text(result))
                },
                )

        } catch (_: ClosedReceiveChannelException) {
            sessions.clearAll()
        } finally {
            appSettings.controllerHelper(
                { command = McblCommand.FINISH },
                { },
            )
        }
    }.collect()
}
