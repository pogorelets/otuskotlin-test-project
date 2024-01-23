package ru.otus.otuskotlin.microblog.stubs

import ru.otus.otuskotlin.common.models.*

object McblTipStubTips {
    val TIP1: McblTip
        get() = McblTip(
            id = McblTipId("123"),
            title = "Тeма",
            description = "Описание топика",
            user = "IronMan",
            countLikes = 3,
            countDislikes = 1,
            datePublic = "21.01.2023",
            ownerId = McblTipOwnerId("user-1"),
            permissions = mutableSetOf(
                McblTipPermissionClient.DELETE,
                McblTipPermissionClient.READ,
                McblTipPermissionClient.UPDATE,
            )
        )
    val TIP2 = TIP1.copy(title = "Тема111")
}
