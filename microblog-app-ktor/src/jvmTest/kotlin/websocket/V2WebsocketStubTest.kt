package ru.otus.otuskotlin.marketplace.app.ktor.websocket

import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.server.testing.*
import kotlinx.coroutines.withTimeout

import ru.otus.otuskotlin.api.v2.apiV2Mapper
import ru.otus.otuskotlin.api.v2.models.*
import ru.otus.otuskotlin.app.ktor.module
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class V2WebsocketStubTest {

    @Test
    fun createStub() {
        val request = TipCreateRequest(
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

        testMethod<IResponse>(request) {
            assertEquals("12345", it.requestId)
        }
    }

    @Test
    fun readStub() {
        val request = TipReadRequest(
            requestId = "12345",
            tip = TipReadObject("123"),
            debug = TipDebug(
                mode = TipRequestDebugMode.STUB,
                stub = TipRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals("12345", it.requestId)
        }
    }

    @Test
    fun updateStub() {
        val request = TipUpdateRequest(
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

        testMethod<IResponse>(request) {
            assertEquals("12345", it.requestId)
        }
    }

    @Test
    fun deleteStub() {
        val request = TipDeleteRequest(
            requestId = "12345",
            tip = TipDeleteObject(
                id = "123",
            ),
            debug = TipDebug(
                mode = TipRequestDebugMode.STUB,
                stub = TipRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals("12345", it.requestId)
        }
    }

    @Test
    fun searchStub() {
        val request = TipSearchRequest(
            requestId = "12345",
            tipFilter = TipSearchFilter(),
            debug = TipDebug(
                mode = TipRequestDebugMode.STUB,
                stub = TipRequestDebugStubs.SUCCESS
            )
        )

        testMethod<IResponse>(request) {
            assertEquals("12345", it.requestId)
        }
    }

    private inline fun <reified T> testMethod(
        request: IRequest,
        crossinline assertBlock: (T) -> Unit
    ) = testApplication {
        application { module() }
        val client = createClient {
            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(apiV2Mapper)
            }
        }

        client.webSocket("/v2/ws") {
            withTimeout(3000) {
                val response = receiveDeserialized<IResponse>() as T
                assertIs<TipInitResponse>(response)
            }
            sendSerialized(request)
            withTimeout(3000) {
                val response = receiveDeserialized<IResponse>() as T
                assertBlock(response)
            }
        }
    }
}
