package ru.otus.otuskotlin.microblog.app.ktor.stub

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import ru.otus.otuskotlin.api.v2.apiV2Mapper
import ru.otus.otuskotlin.api.v2.models.*
import ru.otus.otuskotlin.app.ktor.module
import kotlin.test.Test
import kotlin.test.assertEquals

class V2AdStubApiTest {

    @Test
    fun create() = v2TestApplication {  client ->
        val response = client.post("/v2/tip/create") {
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
    fun read() = v2TestApplication {  client ->
        val response = client.post("/v2/tip/read") {
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
    fun update() = v2TestApplication {  client ->
        val response = client.post("/v2/tip/update") {
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
    fun delete() = v2TestApplication {  client ->
        val response = client.post("/v2/tip/delete") {
            val requestObj = TipDeleteRequest(
                requestId = "12345",
                tip = TipDeleteObject(
                    id = "123",
                    lock = "123"
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
    fun search() = v2TestApplication {  client ->
        val response = client.post("/v2/tip/search") {
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

    private fun v2TestApplication(function: suspend (HttpClient) -> Unit): Unit = testApplication {
        application { module() }
        val client = createClient {
            install(ContentNegotiation) {
                json(apiV2Mapper)
            }
        }
        function(client)
    }

}