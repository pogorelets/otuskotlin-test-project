package ru.otus.otuskotlin.microblog.stubs

import ru.otus.otuskotlin.common.models.McblTip
import ru.otus.otuskotlin.common.models.McblTipId
import ru.otus.otuskotlin.microblog.stubs.McblTipStubTips.TIP1

object McblTipStubs {
    fun get(): McblTip = TIP1.copy()

    fun prepareResult(block: McblTip.() -> Unit): McblTip = get().apply(block)

    fun prepareSearchList() = listOf(
        mcblTipDemand("124", "Тема2"),
        mcblTipDemand("125", "Тема3"),
        mcblTipDemand("126", "Тема4"),
        mcblTipDemand("127", "Тема5"),
        mcblTipDemand("128", "Тема6"),
        mcblTipDemand("129", "Тема7"),
    )

    private fun mcblTipDemand(id: String, title: String) =
        mcblTip(TIP1, id = id,  title = title)

    private fun mcblTip(base: McblTip, id: String, title: String) = base.copy(
        id = McblTipId(id),
        title = title,
    )
}