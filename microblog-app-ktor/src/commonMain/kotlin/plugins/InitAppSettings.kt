package ru.otus.otuskotlin.app.ktor.plugins

import McblAppSettings
import io.ktor.server.application.*
import ru.otus.otuskotlin.app.ktor.base.KtorWsSessionRepo
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.common.McblCorSettings

fun Application.initAppSettings(): McblAppSettings {
    return McblAppSettings(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        processor = McblTipProcessor(),
        corSettings = McblCorSettings(
            wsSessions = KtorWsSessionRepo(),
            ),
    )
}
