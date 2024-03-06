package ru.otus.otuskotlin.microblog.biz.validation

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.microblog.common.helpers.errorValidation
import ru.otus.otuskotlin.microblog.common.helpers.fail

fun CorChainDsl<McblContext>.validateTitleHasContent(title: String) = worker {
    this.title = title
    val regExp = Regex("\\p{L}")
    on { tipValidating.title.isNotEmpty() && ! tipValidating.title.contains(regExp) }
    handle {
        fail(
            errorValidation(
                field = "title",
                violationCode = "noContent",
                description = "field must contain leters"
            )
        )
    }
}
