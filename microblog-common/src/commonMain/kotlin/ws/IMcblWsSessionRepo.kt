package ru.otus.otuskotlin.common.ws

interface IMcblWsSessionRepo {
    fun add(session: IMcblWsSession)
    fun clearAll()
    fun remove(session: IMcblWsSession)
    suspend fun <K> sendAll(obj: K)

    companion object {
        val NONE = object : IMcblWsSessionRepo {
            override fun add(session: IMcblWsSession) {}
            override fun clearAll() {}
            override fun remove(session: IMcblWsSession) {}
            override suspend fun <K> sendAll(obj: K) {}
        }
    }
}
