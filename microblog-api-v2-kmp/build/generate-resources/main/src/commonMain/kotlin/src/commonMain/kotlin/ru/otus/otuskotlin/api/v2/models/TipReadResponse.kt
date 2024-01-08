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

import ru.otus.otuskotlin.api.v2.models.Error
import ru.otus.otuskotlin.api.v2.models.IResponse
import ru.otus.otuskotlin.api.v2.models.ResponseResult
import ru.otus.otuskotlin.api.v2.models.TipResponseObject

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param responseType Поле-дескриминатор для вычисления типа запроса
 * @param requestId Идентификатор запроса для отладки
 * @param result 
 * @param errors 
 * @param tip 
 */
@Serializable

@SerialName(value = "read")
data class TipReadResponse (



    /* Идентификатор запроса для отладки */
    @SerialName(value = "requestId") override val requestId: kotlin.String? = null
,
    @SerialName(value = "result") override val result: ResponseResult? = null
,
    @SerialName(value = "errors") override val errors: kotlin.collections.List<Error>? = null
,
    @SerialName(value = "tip") val tip: TipResponseObject? = null

) : IResponse()

