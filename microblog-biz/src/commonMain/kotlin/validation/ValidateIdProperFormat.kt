package ru.otus.otuskotlin.microblog.biz.validation

import com.crowdproj.kotlin.cor.handlers.CorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblTipId
import ru.otus.otuskotlin.microblog.common.helpers.errorValidation
import ru.otus.otuskotlin.microblog.common.helpers.fail

fun CorChainDsl<McblContext>.validateIdProperFormat(title: String) = worker {
    this.title = title
    val regExp = Regex("^[0-9a-zA-Z-]+$")
    on { tipValidating.id != McblTipId.NONE && ! tipValidating.id.asString().matches(regExp) }
    handle {
        val encodedId = tipValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
                field = "id",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}