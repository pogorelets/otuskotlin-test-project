package ru.otus.otuskotlin.microblog.biz.validation

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.microblog.common.helpers.errorValidation
import ru.otus.otuskotlin.microblog.common.helpers.fail

fun CorChainDsl<McblContext>.validateIdNotEmpty(title: String) = worker {
   this.title = title
    on { tipValidating.id.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "id",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}