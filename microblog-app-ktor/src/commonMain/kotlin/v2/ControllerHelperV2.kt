package ru.otus.otuskotlin.app.ktor.v2

import McblAppSettings
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.otus.otuskotlin.api.v2.models.IRequest
import ru.otus.otuskotlin.api.v2.models.IResponse
import ru.otus.otuskotlin.mappers.v2.fromTransport
import ru.otus.otuskotlin.mappers.v2.toTransportTip
import ru.otus.otuskotlin.microblog.app.common.controllerHelper

suspend inline fun <reified Q : IRequest, @Suppress("unused") reified R : IResponse> ApplicationCall.processV2(
    appSettings: McblAppSettings,
) = appSettings.controllerHelper(
    {
        fromTransport(receive<Q>())
    },
    {
        respond(toTransportTip())
    },
)
