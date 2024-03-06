package ru.otus.otuskotlin.microblog.biz.groups

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.chain
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.common.models.McblWorkMode


fun CorChainDsl<McblContext>.stubs(title: String, block: CorChainDsl<McblContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == McblWorkMode.STUB && state == McblState.RUNNING }
}