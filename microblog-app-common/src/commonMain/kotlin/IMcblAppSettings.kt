package ru.otus.otuskotlin.microblog.app.common

import ru.otus.otuskotlin.microblog.biz.McblTipProcessor
import ru.otus.otuskotlin.microblog.common.McblCorSettings

interface IMcblAppSettings {
    val processor: McblTipProcessor
    val corSettings: McblCorSettings
}
