package validation

import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import kotlin.test.Test

class BizValidationCreateTest {

    private val command = McblCommand.CREATE
    private val processor by lazy { McblTipProcessor() }

    @Test fun correctTitle() = validationTitleCorrect(command, processor)
    @Test fun emptyTitle() = validationTitleEmpty(command, processor)
    @Test fun correctDescription() = validationDescriptionCorrect(command, processor)
    @Test fun emptyDescription() = validationDescriptionEmpty(command, processor)
}