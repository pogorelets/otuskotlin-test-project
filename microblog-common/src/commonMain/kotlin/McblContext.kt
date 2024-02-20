package ru.otus.otuskotlin.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.common.ws.IMcblWsSession

data class McblContext(
    var command: McblCommand = McblCommand.NONE,
    var state: McblState = McblState.NONE,
    val errors: MutableList<McblError> = mutableListOf(),
    var workMode: McblWorkMode = McblWorkMode.PROD,
    var stubCase: McblStubs = McblStubs.NONE,
    var requestId: McblRequestId = McblRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var tipRequest: McblTip = McblTip(),
    var tipFilterRequest: McblTipFilter = McblTipFilter(),
    var tipResponse: McblTip = McblTip(),
    var tipsResponse: MutableList<McblTip> = mutableListOf(),
    var session: IMcblWsSession = IMcblWsSession.NONE
)
