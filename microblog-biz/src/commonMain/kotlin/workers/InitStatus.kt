package ru.otus.otuskotlin.microblog.biz.workers

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblState

fun CorChainDsl<McblContext>.initStatus(title: String) = worker{
    this.title = title
    description = """
        Инициализация статусов бизнес-процессов. Статус может прийти снаружи. В большинстве случаев мы 
        получаем снаружи NONE. Поэтому просто проверка на NONE, если да , то инициализируем.
    """.trimIndent()
    on { state ==  McblState.NONE }
    handle { state = McblState.RUNNING }
}