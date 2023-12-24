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

package ru.otus.otuskotlin.api.v1.models


import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 * Базовый интерфейс для всех запросов
 *
 * @param requestType Поле-дескриминатор для вычисления типа запроса
 * @param requestId Идентификатор запроса для отладки
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "requestType", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = TipCreateRequest::class, name = "create"),
    JsonSubTypes.Type(value = TipDeleteRequest::class, name = "delete"),
    JsonSubTypes.Type(value = TipOffersRequest::class, name = "offers"),
    JsonSubTypes.Type(value = TipReadRequest::class, name = "read"),
    JsonSubTypes.Type(value = TipSearchRequest::class, name = "search"),
    JsonSubTypes.Type(value = TipUpdateRequest::class, name = "update")
)

interface IRequest {

    /* Поле-дескриминатор для вычисления типа запроса */
    @get:JsonProperty("requestType")
    val requestType: kotlin.String?
    /* Идентификатор запроса для отладки */
    @get:JsonProperty("requestId")
    val requestId: kotlin.String?
}
