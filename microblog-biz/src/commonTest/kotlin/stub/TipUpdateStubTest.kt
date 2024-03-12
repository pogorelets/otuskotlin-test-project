package stub

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import kotlin.test.Test
import kotlin.test.assertEquals

class TipUpdateStubTest {
    private val processor = McblTipProcessor()
    val id = McblTipId("456")
    val title = "title 456"
    val description = "desc 456"
    val user = "IronMan"

    @Test
    fun create() = runTest {

        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.SUCCESS,
            tipRequest = McblTip(
                id = id,
                title = title,
                description = description,
                user = user,
            ),
        )
        processor.exec(ctx)
        assertEquals(id, ctx.tipResponse.id)
        assertEquals(title, ctx.tipResponse.title)
        assertEquals(description, ctx.tipResponse.description)
        assertEquals(user, ctx.tipResponse.user)
    }

    @Test
    fun badId() = runTest {
        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_ID,
            tipRequest = McblTip(),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badTitle() = runTest {
        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_TITLE,
            tipRequest = McblTip(
                id = id,
                title = title,
                description = description,
                user = user,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("title", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
    @Test
    fun badDescription() = runTest {
        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_DESCRIPTION,
            tipRequest = McblTip(
                id = id,
                title = title,
                description = description,
                user = user,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("description", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.DB_ERROR,
            tipRequest = McblTip(
                id = id,
                title = title,
                description = description,
                user = user,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = McblContext(
            command = McblCommand.UPDATE,
            state = McblState.NONE,
            workMode = McblWorkMode.STUB,
            stubCase = McblStubs.BAD_SEARCH_STRING,
            tipRequest = McblTip(
                id = id,
                title = title,
                description = description,
                user = user,
            ),
        )
        processor.exec(ctx)
        assertEquals(McblTip(), ctx.tipResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}