package ru.otus.otuskotlin.microblog.biz

import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.common.models.McblWorkMode
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs

class McblTipProcessor {
    suspend fun exec(ctx: McblContext) {
        require(ctx.workMode == McblWorkMode.STUB) {
            "Currently working only in STUB mode."
        }

        if (ctx.command == McblCommand.SEARCH) {
            ctx.tipsResponse.addAll(McblTipStubs.prepareSearchList())
        } else {
            ctx.tipResponse = McblTipStubs.get()
        }
    }
}