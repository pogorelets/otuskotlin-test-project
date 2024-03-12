package ru.otus.otuskotlin.microblog.biz.workers

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs


fun CorChainDsl<McblContext>.stubCreateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == McblStubs.SUCCESS &&  state == McblState.RUNNING }
    handle {
        state = McblState.FINISHING
        val stub = McblTipStubs.prepareResult {
            tipRequest.title.takeIf { it.isNotBlank() }?.also { this.title = it }
            tipRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
        }
        tipResponse = stub
    }
}