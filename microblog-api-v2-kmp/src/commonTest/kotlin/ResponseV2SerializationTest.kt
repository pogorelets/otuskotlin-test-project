package ru.otus.otuskotlin.api.v2

import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV2SerializationTest {
    private val response: IResponse = TipCreateResponse(
        requestId = "123",
        tip = TipResponseObject(
            title = "tip title",
            description = "tip description",
            user = "userName"
        )
    )

    @Test
    fun serialize() {
        val json = apiV2Mapper.encodeToString(response)

        println(json)

        assertContains(json, Regex("\"title\":\\s*\"tip title\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(response)
        val obj = apiV2Mapper.decodeFromString(json) as TipCreateResponse

        assertEquals(response, obj)
    }
}