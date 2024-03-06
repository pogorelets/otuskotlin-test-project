package ru.otus.otuskotlin.microblog.biz.groups

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.chain
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.common.models.McblState

fun CorChainDsl<McblContext>.operation(title: String, command: McblCommand, block: CorChainDsl<McblContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == McblState.RUNNING }
}