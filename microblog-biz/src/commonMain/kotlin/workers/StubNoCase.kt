package ru.otus.otuskotlin.microblog.biz.workers

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblError
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.microblog.common.helpers.fail

fun CorChainDsl<McblContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == McblState.RUNNING }
    handle {
        fail(
            McblError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}