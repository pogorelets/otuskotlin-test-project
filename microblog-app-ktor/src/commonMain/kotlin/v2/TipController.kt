package ru.otus.otuskotlin.app.ktor.v2

import McblAppSettings
import io.ktor.server.application.*
import ru.otus.otuskotlin.api.v2.models.*

suspend fun ApplicationCall.createTip(appSettings: McblAppSettings) =
    processV2<TipCreateRequest, TipCreateResponse>(appSettings)
suspend fun ApplicationCall.readTip(appSettings: McblAppSettings) =
    processV2<TipReadRequest, TipReadResponse>(appSettings)
suspend fun ApplicationCall.updateTip(appSettings: McblAppSettings) =
    processV2<TipUpdateRequest, TipUpdateResponse>(appSettings)
suspend fun ApplicationCall.deleteTip(appSettings: McblAppSettings) =
    processV2<TipDeleteRequest, TipDeleteResponse>(appSettings)
suspend fun ApplicationCall.searchTip(appSettings: McblAppSettings) =
    processV2<TipSearchRequest, TipSearchResponse>(appSettings)









