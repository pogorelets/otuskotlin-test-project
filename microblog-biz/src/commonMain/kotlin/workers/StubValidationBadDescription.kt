package ru.otus.otuskotlin.microblog.biz.workers

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblError
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.common.stubs.McblStubs

fun CorChainDsl<McblContext>.stubValidationBadDescription(title: String) = worker {
    this.title = title
    on { stubCase == McblStubs.BAD_DESCRIPTION && state == McblState.RUNNING }
    handle {
        state = McblState.FAILING
        this.errors.add(
            McblError(
                group = "validation",
                code = "validation-description",
                field = "description",
                message = "Wrong description field"
            )
        )
    }
}