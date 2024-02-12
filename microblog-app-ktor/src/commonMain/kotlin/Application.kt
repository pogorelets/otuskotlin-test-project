package ru.otus.otuskotlin.app.ktor

import McblAppSettings
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import ru.otus.otuskotlin.api.v2.apiV2Mapper
import ru.otus.otuskotlin.app.ktor.base.KtorWsSessionRepo
import ru.otus.otuskotlin.app.ktor.plugins.initAppSettings
import ru.otus.otuskotlin.app.ktor.v2.v2Tip
import ru.otus.otuskotlin.app.ktor.v2.wsHandlerV2
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor

fun Application.module(
    appSettings: McblAppSettings = initAppSettings()
    ){

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowCredentials = true
    }

    install(WebSockets)

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        route("v2") {
            install(ContentNegotiation) {
                json(apiV2Mapper)
            }
            v2Tip(appSettings)
            webSocket("/ws") {
                wsHandlerV2(appSettings)
            }
        }
    }
}
