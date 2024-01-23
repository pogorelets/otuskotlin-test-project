package ru.otus.otuskotlin.microblog.app.ktor.stub

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.microblog.app.ktor.moduleJvm
import kotlin.test.Test
import kotlin.test.assertEquals

class V1AdStubApiTest {
    @Test
    fun create() = v1TestApplication { client ->
        val response = client.post("/v1/tip/create") {
            val requestObj = TipCreateRequest(
                requestId = "12345",
                tip = TipCreateObject(
                    title = "Тeма",
                    description = "Описание топика",
                    user = "IronMan",
                    countLikes = 3,
                    countDislikes = 1,
                    datePublic = "21.01.2023",
                ),
                debug = TipDebug(
                    mode = TipRequestDebugMode.STUB,
                    stub = TipRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<TipCreateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("123", responseObj.tip?.id)
    }


    @Test
    fun read() = v1TestApplication { client ->
        val response = client.post("/v1/tip/read") {
            val requestObj = TipReadRequest(
                requestId = "12345",
                tip = TipReadObject("123"),
                debug = TipDebug(
                    mode = TipRequestDebugMode.STUB,
                    stub = TipRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<TipReadResponse>()
        assertEquals(200, response.status.value)
        assertEquals("123", responseObj.tip?.id)
    }

    @Test
    fun update() = v1TestApplication { client ->
        val response = client.post("/v1/tip/update") {
            val requestObj = TipUpdateRequest(
                requestId = "12345",
                tip = TipUpdateObject(
                    id = "123",
                    title = "Тeма",
                    description = "Описание топика",
                ),
                debug = TipDebug(
                    mode = TipRequestDebugMode.STUB,
                    stub = TipRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<TipUpdateResponse>()
        assertEquals(200, response.status.value)
        assertEquals("123", responseObj.tip?.id)
    }

    @Test
    fun delete() = v1TestApplication { client ->
        val response = client.post("/v1/tip/delete") {
            val requestObj = TipDeleteRequest(
                requestId = "12345",
                tip = TipDeleteObject(
                    id = "123",
                ),
                debug = TipDebug(
                    mode = TipRequestDebugMode.STUB,
                    stub = TipRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<TipDeleteResponse>()
        assertEquals(200, response.status.value)
        assertEquals("123", responseObj.tip?.id)
    }

    @Test
    fun search() = v1TestApplication { client ->
        val response = client.post("/v1/tip/search") {
            val requestObj = TipSearchRequest(
                requestId = "12345",
                tipFilter = TipSearchFilter(),
                debug = TipDebug(
                    mode = TipRequestDebugMode.STUB,
                    stub = TipRequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<TipSearchResponse>()
        assertEquals(200, response.status.value)
        assertEquals("124", responseObj.tips?.first()?.id)
    }


    private fun v1TestApplication(function: suspend (HttpClient) -> Unit): Unit = testApplication {
        application { moduleJvm() }
        val client = createClient {
            install(ContentNegotiation) {
                jackson {
                    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

                    enable(SerializationFeature.INDENT_OUTPUT)
                    writerWithDefaultPrettyPrinter()
                }
            }
        }
        function(client)
    }
}