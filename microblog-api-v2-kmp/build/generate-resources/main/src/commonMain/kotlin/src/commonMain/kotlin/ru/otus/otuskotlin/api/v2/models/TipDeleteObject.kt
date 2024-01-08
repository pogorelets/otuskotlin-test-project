/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package ru.otus.otuskotlin.api.v2.models


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param id Идентификатор комментария (совета))
 * @param lock Версия оптимистичной блокировки
 */
@Serializable

data class TipDeleteObject (

    /* Идентификатор комментария (совета)) */
    @SerialName(value = "id") val id: kotlin.String? = null
,

    /* Версия оптимистичной блокировки */
    @SerialName(value = "lock") val lock: kotlin.String? = null

)

