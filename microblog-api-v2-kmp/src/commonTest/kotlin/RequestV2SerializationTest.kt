package ru.otus.otuskotlin.api.v2

import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV2SerializationTest {
    private val request: IRequest = TipCreateRequest(
        requestId = "123",
        debug = TipDebug(
            mode = TipRequestDebugMode.STUB,
            stub = TipRequestDebugStubs.BAD_TITLE
        ),
        tip = TipCreateObject(
            title = "tip title",
            description = "tip description",
            user = "userName"
        )
    )

    @Test
    fun serialize() {
        val json = apiV2Mapper.encodeToString(IRequest.serializer(), request)

        println(json)

        assertContains(json, Regex("\"title\":\\s*\"tip title\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(request)
        val obj = apiV2Mapper.decodeFromString(json) as TipCreateRequest

        assertEquals(request, obj)
    }
    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV2Mapper.decodeFromString<TipCreateRequest>(jsonString)

        assertEquals("123", obj.requestId)
    }
}