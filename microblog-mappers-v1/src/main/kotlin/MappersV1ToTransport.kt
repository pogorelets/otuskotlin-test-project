package ru.otus.otuskotlin.mappers.v1

import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.mappers.v1.exceptions.UnknownMcblCommand

fun McblContext.toTransportAd(): IResponse = when (val cmd = command) {
    McblCommand.CREATE -> toTransportCreate()
    McblCommand.READ -> toTransportRead()
    McblCommand.UPDATE -> toTransportUpdate()
    McblCommand.DELETE -> toTransportDelete()
    McblCommand.SEARCH -> toTransportSearch()
    McblCommand.OFFERS -> toTransportOffers()
    McblCommand.NONE -> throw UnknownMcblCommand(cmd)
}

fun McblContext.toTransportCreate() = TipCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tip = tipResponse.toTransportTip()
)

fun McblContext.toTransportRead() = TipReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tip = tipResponse.toTransportTip()
)

fun McblContext.toTransportUpdate() = TipUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tip = tipResponse.toTransportTip()
)

fun McblContext.toTransportDelete() = TipDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tip = tipResponse.toTransportTip()
)

fun McblContext.toTransportSearch() = TipSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tips = tipsResponse.toTransportTip()
)

fun McblContext.toTransportOffers() = TipOffersResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == McblState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    tips = tipsResponse.toTransportTip()
)

fun List<McblTip>.toTransportTip(): List<TipResponseObject>? = this
    .map { it.toTransportTip() }
    .toList()
    .takeIf { it.isNotEmpty() }

fun McblContext.toTransportInit() = TipInitResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (errors.isEmpty()) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
)

private fun McblTip.toTransportTip(): TipResponseObject = TipResponseObject(
    id = id.takeIf { it != McblTipId.NONE }?.asString(),
    title = title.takeIf { it.isNotBlank() },
    user = user.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    countLikes = countLikes,
    countDislikes = countDislikes,
    datePublic = datePublic.takeIf { it.isNotBlank() },
    ownerId = ownerId.takeIf { it != McblTipOwnerId.NONE }?.asString(),
    permissions = permissions.toTransportTip(),
)

private fun Set<McblTipPermissionClient>.toTransportTip(): Set<TipPermissions>? = this
    .map { it.toTransportTip() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun McblTipPermissionClient.toTransportTip() = when (this) {
    McblTipPermissionClient.READ -> TipPermissions.READ
    McblTipPermissionClient.UPDATE -> TipPermissions.UPDATE
    McblTipPermissionClient.DELETE -> TipPermissions.DELETE
}

private fun List<McblError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportTip() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun McblError.toTransportTip() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)
