package ru.otus.otuskotlin.microblog.app.ktor

import McblAppSettings
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.slf4j.event.Level
import ru.otus.otuskotlin.api.v1.apiV1Mapper
import ru.otus.otuskotlin.app.ktor.module
import ru.otus.otuskotlin.app.ktor.plugins.initAppSettings
import ru.otus.otuskotlin.app.ktor.v1.wsHandlerV1
import v1.v1Tip

@Suppress("unused")
fun Application.moduleJvm(
    appSettings: McblAppSettings = initAppSettings()
) {
    install(CachingHeaders)
    install(DefaultHeaders)
    install(AutoHeadResponse)
    install(CallLogging) {
        level = Level.INFO
    }
    module(appSettings)

    routing {
        route("v1") {
            install(ContentNegotiation) {
                jackson {
                    setConfig(apiV1Mapper.serializationConfig)
                    setConfig(apiV1Mapper.deserializationConfig)
                }
            }
            v1Tip(appSettings)
            webSocket("/ws") {
                wsHandlerV1(appSettings)
            }
        }
    }
}
