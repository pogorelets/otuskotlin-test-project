package ru.otus.otuskotlin.microblog.biz.workers

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblError
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.common.stubs.McblStubs

fun CorChainDsl<McblContext>.stubValidationBadId(title: String) = worker {
    this.title = title
    on { stubCase == McblStubs.BAD_ID && state == McblState.RUNNING }
    handle {
        state = McblState.FAILING
        this.errors.add(
            McblError(
                group = "validation",
                code = "validation-id",
                field = "id",
                message = "Wrong id field"
            )
        )
    }
}