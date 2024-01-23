package ru.otus.otuskotlin.app.ktor.v2

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.otus.otuskotlin.microblog.biz.McblTipProcessor


fun Route.v2Tip(processor: McblTipProcessor) {
    route("tip") {
        post("create") {
            call.createTip(processor)
        }
        post("read") {
            call.readTip(processor)
        }
        post("update") {
            call.updateTip(processor)
        }
        post("delete") {
            call.deleteTip(processor)
        }
        post("search") {
            call.searchTip(processor)
        }
    }
}
