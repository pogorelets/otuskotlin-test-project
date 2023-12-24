package ru.otus.otuskotlin.api.v1

import ru.otus.otuskotlin.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {
    private val request = TipCreateRequest(
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
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"title\":\\s*\"tip title\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as TipCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, TipCreateRequest::class.java)

        assertEquals("123", obj.requestId)
    }
}