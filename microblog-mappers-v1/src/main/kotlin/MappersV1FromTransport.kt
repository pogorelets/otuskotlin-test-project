package ru.otus.otuskotlin.mappers.v1

import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.mappers.v1.exceptions.UnknownRequestClass

fun McblContext.fromTransport(request: IRequest) = when (request) {
    is TipCreateRequest -> fromTransport(request)
    is TipReadRequest -> fromTransport(request)
    is TipUpdateRequest -> fromTransport(request)
    is TipDeleteRequest -> fromTransport(request)
    is TipSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toTipId() = this?.let { McblTipId(it) } ?: McblTipId.NONE
private fun String?.toTipWithId() = McblTip(id = this.toTipId())
private fun IRequest?.requestId() = this?.requestId?.let { McblRequestId(it) } ?: McblRequestId.NONE

private fun TipDebug?.transportToWorkMode(): McblWorkMode = when (this?.mode) {
    TipRequestDebugMode.PROD -> McblWorkMode.PROD
    TipRequestDebugMode.TEST -> McblWorkMode.TEST
    TipRequestDebugMode.STUB -> McblWorkMode.STUB
    null -> McblWorkMode.PROD
}

private fun TipDebug?.transportToStubCase(): McblStubs = when (this?.stub) {
    TipRequestDebugStubs.SUCCESS -> McblStubs.SUCCESS
    TipRequestDebugStubs.NOT_FOUND -> McblStubs.NOT_FOUND
    TipRequestDebugStubs.BAD_ID -> McblStubs.BAD_ID
    TipRequestDebugStubs.BAD_TITLE -> McblStubs.BAD_TITLE
    TipRequestDebugStubs.BAD_DESCRIPTION -> McblStubs.BAD_DESCRIPTION
    TipRequestDebugStubs.BAD_SEARCH_DATE -> McblStubs.BAD_SEARCH_DATE
    TipRequestDebugStubs.CANNOT_DELETE -> McblStubs.CANNOT_DELETE
    TipRequestDebugStubs.BAD_SEARCH_STRING -> McblStubs.BAD_SEARCH_STRING
    null -> McblStubs.NONE
}

fun McblContext.fromTransport(request: TipCreateRequest) {
    command = McblCommand.CREATE
    requestId = request.requestId()
    tipRequest = request.tip?.toInternal() ?: McblTip()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun McblContext.fromTransport(request: TipReadRequest) {
    command = McblCommand.READ
    requestId = request.requestId()
    tipRequest = request.tip?.id.toTipWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun McblContext.fromTransport(request: TipUpdateRequest) {
    command = McblCommand.UPDATE
    requestId = request.requestId()
    tipRequest = request.tip?.toInternal() ?: McblTip()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun McblContext.fromTransport(request: TipDeleteRequest) {
    command = McblCommand.DELETE
    requestId = request.requestId()
    tipRequest = request.tip?.id.toTipWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun McblContext.fromTransport(request: TipSearchRequest) {
    command = McblCommand.SEARCH
    requestId = request.requestId()
    tipFilterRequest = request.tipFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}
private fun TipSearchFilter?.toInternal(): McblTipFilter = McblTipFilter(
    searchString = this?.searchString ?: "",
    searchDate =this?.searchDate?: "",
    searchPopular = this?.searchPopular?: true,
)

private fun TipCreateObject.toInternal(): McblTip = McblTip(
    title = this.title ?: "",
    description = this.description ?: "",
    user = this.user ?: "",
    countLikes = this?.countLikes ?: 0,
    countDislikes = this?.countDislikes ?: 0,
    datePublic = this?.datePublic ?: "",
)

private fun TipUpdateObject.toInternal(): McblTip = McblTip(
    id = this.id.toTipId(),
    title = this.title ?: "",
    description = this.description ?: "",
    user = this.user ?: "",
    countLikes = this?.countLikes ?: 0,
    countDislikes = this?.countDislikes ?: 0,
    datePublic = this?.datePublic ?: "",
)

