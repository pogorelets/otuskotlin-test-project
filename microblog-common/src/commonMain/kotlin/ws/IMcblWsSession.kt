package ru.otus.otuskotlin.common.ws

interface IMcblWsSession {
    suspend fun <T> send(obj: T)
    companion object {
        val NONE = object : IMcblWsSession {
            override suspend fun <T> send(obj: T) {

            }
        }
    }
}
