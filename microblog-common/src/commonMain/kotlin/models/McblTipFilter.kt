package ru.otus.otuskotlin.common.models

data class McblTipFilter(
    var searchString: String = "",
    val searchDate: String = "",
    val searchPopular: Boolean = true,
)