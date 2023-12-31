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
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Базовый интерфейс для всех запросов
 *
 * @param requestType Поле-дескриминатор для вычисления типа запроса
 * @param requestId Идентификатор запроса для отладки
 */
@Serializable

@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("requestType")
sealed class IRequest {


    /* Идентификатор запроса для отладки */
    @SerialName(value = "requestId") abstract val requestId: kotlin.String?

}

