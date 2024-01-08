package ru.otus.otuskotlin.api.v1

import ru.otus.otuskotlin.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {
    private val response = TipCreateResponse(
        requestId = "123",
        tip = TipResponseObject(
            title = "tip title",
            description = "tip description",
            user = "Username",
            id = "tip234",
            ownerId = "2323"
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"title\":\\s*\"tip title\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as TipCreateResponse

        assertEquals(response, obj)
    }
}