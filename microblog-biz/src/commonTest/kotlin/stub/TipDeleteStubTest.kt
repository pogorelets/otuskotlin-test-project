package stub

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.stubs.McblTipStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class TipDeleteStubTest {
    private val processor = McblTipProcessor()
    val id = McblTipId("123")

    @Test
    fun delete() = runTest {

        val ctx = McblContext(
            command = McblCommand.DELETE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.SUCCESS,
            tipRequest = McblTip(
                id = id,
            ),
        )
        processor.exec(ctx)

        val stub = McblTipStubs.get()
        assertEquals(stub.id, ctx.tipResponse.id)
        assertEquals(stub.title, ctx.tipResponse.title)
        assertEquals(stub.description, ctx.tipResponse.description)
        assertEquals(stub.user, ctx.tipResponse.user)
    }

    @Test
    fun badId() = runTest {
        val ctx = McblContext(
            command = McblCommand.DELETE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_ID,
            tipRequest = McblTip()
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = McblContext(
            command = McblCommand.DELETE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.DB_ERROR,
            tipRequest = McblTip(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = McblContext(
            command = McblCommand.DELETE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_TITLE,
            tipRequest = McblTip(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
    }
}