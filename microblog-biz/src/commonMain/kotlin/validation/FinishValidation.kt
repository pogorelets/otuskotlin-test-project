package ru.otus.otuskotlin.microblog.biz.validation

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState

fun CorChainDsl<McblContext>.finishTipValidation(title: String) = worker {
    this.title = title
    on { state == McblState.RUNNING }
    handle {
        tipValidated = tipValidating
    }
}

fun CorChainDsl<McblContext>.finishTipFilterValidation(title: String) = worker {
    this.title = title
    on { state == McblState.RUNNING }
    handle {
        tipFilterValidated = tipFilterValidating
    }
}