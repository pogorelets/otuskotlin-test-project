package v1

import McblAppSettings
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Route.v1Tip(appSettings: McblAppSettings) {
    route("tip") {
        post("create") {
            call.createTip(appSettings)
        }
        post("read") {
            call.readTip(appSettings)
        }
        post("update") {
            call.updateTip(appSettings)
        }
        post("delete") {
            call.deleteTip(appSettings)
        }
        post("search") {
            call.searchTip(appSettings)
        }
    }
}