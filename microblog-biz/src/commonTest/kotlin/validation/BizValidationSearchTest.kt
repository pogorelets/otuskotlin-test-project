package validation

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.McblCommand
import ru.otus.otuskotlin.common.models.McblState
import ru.otus.otuskotlin.common.models.McblTipFilter
import ru.otus.otuskotlin.common.models.McblWorkMode
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BizValidationSearchTest {

    private val command = McblCommand.SEARCH
    private val processor by lazy { McblTipProcessor() }

    @Test
    fun correctEmpty() = runTest {
        val ctx = McblContext(
            command = command,
            state = McblState.NONE,
            workMode = McblWorkMode.TEST,
            tipFilterRequest = McblTipFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(McblState.FAILING, ctx.state)
    }
}