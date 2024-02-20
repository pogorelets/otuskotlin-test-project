package ru.otus.otuskotlin.app.ktor.base

import io.ktor.websocket.*
import ru.otus.otuskotlin.api.v2.models.IResponse
import ru.otus.otuskotlin.common.ws.IMcblWsSession
import ru.otus.otuskotlin.api.v2.apiV2ResponseSerialize

data class KtorWsSessionV2(
    private val session: WebSocketSession
) : IMcblWsSession {
    override suspend fun <T> send(obj: T) {
        require(obj is IResponse)
        session.send(Frame.Text(apiV2ResponseSerialize(obj)))
    }
}
