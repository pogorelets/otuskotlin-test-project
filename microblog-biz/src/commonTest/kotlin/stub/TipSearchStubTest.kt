package stub

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class TipSearchStubTest {
    private val processor = McblTipProcessor()
    val filter = McblTipFilter(searchString = "Тема")

    @Test
    fun read() = runTest {

        val ctx = McblContext(
            command = McblCommand.SEARCH,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.SUCCESS,
            tipFilterRequest = filter,
        )
        processor.exec(ctx)
        assertTrue(ctx.tipsResponse.size > 1)
        val first = ctx.tipsResponse.firstOrNull() ?: fail("Empty response list")
        assertTrue(first.title.contains(filter.searchString))
        with (McblTipStubs.get()) {
            assertEquals(user, first.user)
        }
    }

    @Test
    fun badId() = runTest {
        val ctx = McblContext(
            command = McblCommand.SEARCH,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_ID,
            tipFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = McblContext(
            command = McblCommand.SEARCH,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.DB_ERROR,
            tipFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = McblContext(
            command = McblCommand.SEARCH,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_TITLE,
            tipFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}