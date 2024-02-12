package v1

import McblAppSettings
import io.ktor.server.application.*
import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.app.ktor.v1.processV1

suspend fun ApplicationCall.createTip(appSettings: McblAppSettings) =
    processV1<TipCreateRequest, TipCreateResponse>(appSettings)

suspend fun ApplicationCall.readTip(appSettings: McblAppSettings) =
    processV1<TipReadRequest, TipReadResponse>(appSettings)

suspend fun ApplicationCall.updateTip(appSettings: McblAppSettings) =
    processV1<TipUpdateRequest, TipUpdateResponse>(appSettings)
suspend fun ApplicationCall.deleteTip(appSettings: McblAppSettings) =
    processV1<TipDeleteRequest, TipDeleteResponse>(appSettings)
suspend fun ApplicationCall.searchTip(appSettings: McblAppSettings) =
    processV1<TipSearchRequest, TipSearchResponse>(appSettings)