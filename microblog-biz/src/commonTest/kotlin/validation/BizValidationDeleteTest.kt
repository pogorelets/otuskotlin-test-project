package validation

import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor

import kotlin.test.Test

class BizValidationDeleteTest {

    private val command = McblCommand.DELETE
    private val processor by lazy { McblTipProcessor() }

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
}