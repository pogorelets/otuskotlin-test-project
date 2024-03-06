package ru.otus.otuskotlin.microblog.biz.validation

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.chain
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState

fun CorChainDsl<McblContext>.validation(block: CorChainDsl<McblContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == McblState.RUNNING }
}