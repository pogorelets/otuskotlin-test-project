package ru.otus.otuskotlin.app.ktor.base

import ru.otus.otuskotlin.common.ws.IMcblWsSession
import ru.otus.otuskotlin.common.ws.IMcblWsSessionRepo


class KtorWsSessionRepo: IMcblWsSessionRepo {
    private val sessions: MutableSet<IMcblWsSession> = mutableSetOf()
    override fun add(session: IMcblWsSession) {
        sessions.add(session)
    }

    override fun clearAll() {
        sessions.clear()
    }

    override fun remove(session:  IMcblWsSession) {
        sessions.remove(session)
    }

    override suspend fun <T> sendAll(obj: T) {
        sessions.forEach { it.send(obj) }
    }
}
