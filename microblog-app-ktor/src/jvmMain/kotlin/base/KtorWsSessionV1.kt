package ru.otus.otuskotlin.app.ktor.base

import io.ktor.websocket.*
import ru.otus.otuskotlin.api.v1.apiV1ResponseSerialize
import ru.otus.otuskotlin.api.v1.models.IResponse
import ru.otus.otuskotlin.common.ws.IMcblWsSession

data class KtorWsSessionV1(
    private val session: WebSocketSession
) : IMcblWsSession {
    override suspend fun <T> send(obj: T) {
        require(obj is IResponse)
        session.send(Frame.Text(apiV1ResponseSerialize(obj)))
    }
}
