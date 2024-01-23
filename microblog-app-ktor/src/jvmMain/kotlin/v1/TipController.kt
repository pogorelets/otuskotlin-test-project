package v1

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.otus.otuskotlin.api.v1.models.*
import ru.otus.otuskotlin.common.McblContext
import ru.otus.otuskotlin.mappers.v1.*
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor

suspend fun ApplicationCall.createTip(processor: McblTipProcessor) {
    val request = receive<TipCreateRequest>()
    val context = McblContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportCreate())
}

suspend fun ApplicationCall.readTip(processor: McblTipProcessor) {
    val request = receive<TipReadRequest>()
    val context = McblContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportRead())
}

suspend fun ApplicationCall.updateTip(processor: McblTipProcessor) {
    val request = receive<TipUpdateRequest>()
    val context = McblContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportUpdate())
}

suspend fun ApplicationCall.deleteTip(processor: McblTipProcessor) {
    val request = receive<TipDeleteRequest>()
    val context = McblContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportDelete())
}

suspend fun ApplicationCall.searchTip(processor: McblTipProcessor) {
    val request = receive<TipSearchRequest>()
    val context = McblContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportSearch())
}