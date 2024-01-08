package ru.otus.otuskotlin.mappers.v1

import org.junit.Test
import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.common.models.*
import ru.otus.otuskotlin.common.stubs.McblStubs
import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val req = TipCreateRequest(
            requestId = "1234",
            debug = TipDebug(
                mode = TipRequestDebugMode.STUB,
                stub = TipRequestDebugStubs.SUCCESS,
            ),
            tip = TipCreateObject(
                title = "title",
                description = "desc",
                user = "user",
                countLikes = 3,
                countDislikes = 1,
                datePublic = "2023.12.01"
            ),
        )

        val context = McblContext()
        context.fromTransport(req)

        assertEquals(McblStubs.SUCCESS, context.stubCase)
        assertEquals(McblWorkMode.STUB, context.workMode)
        assertEquals("title", context.tipRequest.title)
        assertEquals("desc", context.tipRequest.description)
        assertEquals("user", context.tipRequest.user)
        assertEquals(3, context.tipRequest.countLikes)
        assertEquals(1, context.tipRequest.countDislikes)
        assertEquals("2023.12.01", context.tipRequest.datePublic)
    }

    @Test
    fun toTransport() {
        val context = McblContext(
            requestId = McblRequestId("1234"),
            command = McblCommand.CREATE,
            tipResponse = McblTip(
                title = "title",
                description = "desc",
                user = "user",
                countLikes = 3,
                countDislikes = 1,
                datePublic = "2023.12.01"
            ),
            errors = mutableListOf(
                McblError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = McblState.RUNNING,
        )

        val req = context.toTransportCreate() as TipCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("title", req.tip?.title)
        assertEquals("desc", req.tip?.description)
        assertEquals("user", req.tip?.user)
        assertEquals(3, req.tip?.countLikes)
        assertEquals(1, req.tip?.countDislikes)
        assertEquals("2023.12.01", req.tip?.datePublic)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}
