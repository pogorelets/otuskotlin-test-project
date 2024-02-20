package ru.otus.otuskotlin.microblog.common

import ru.otus.otuskotlin.common.ws.IMcblWsSessionRepo

data class McblCorSettings(
    val wsSessions: IMcblWsSessionRepo = IMcblWsSessionRepo.NONE,
) {
    companion object {
        val NONE = McblCorSettings()
    }
}
