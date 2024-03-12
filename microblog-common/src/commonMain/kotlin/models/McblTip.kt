package ru.otus.otuskotlin.common.models
data class McblTip(
    var id: McblTipId = McblTipId.NONE,
    var title: String = "",
    var description: String = "",
    var user: String = "",
    var countLikes: Int = 0,
    var countDislikes: Int = 0,
    var datePublic: String = "",
    var ownerId: McblTipOwnerId = McblTipOwnerId.NONE,
    var lock: McblTipLock = McblTipLock.NONE,
    val permissions: MutableSet<McblTipPermissionClient> = mutableSetOf()
){
    fun deepCopy(): McblTip = copy()
}
