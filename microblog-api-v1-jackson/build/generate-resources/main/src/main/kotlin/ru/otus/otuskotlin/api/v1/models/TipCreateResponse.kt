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

import ru.otus.otuskotlin.api.v1.models.Error
import ru.otus.otuskotlin.api.v1.models.IResponse
import ru.otus.otuskotlin.api.v1.models.ResponseResult
import ru.otus.otuskotlin.api.v1.models.TipResponseObject

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 *
 * @param responseType Поле-дескриминатор для вычисления типа запроса
 * @param requestId Идентификатор запроса для отладки
 * @param result 
 * @param errors 
 * @param tip 
 */


data class TipCreateResponse (

    /* Поле-дескриминатор для вычисления типа запроса */
    @field:JsonProperty("responseType")
    override val responseType: kotlin.String? = null,

    /* Идентификатор запроса для отладки */
    @field:JsonProperty("requestId")
    override val requestId: kotlin.String? = null,

    @field:JsonProperty("result")
    override val result: ResponseResult? = null,

    @field:JsonProperty("errors")
    override val errors: kotlin.collections.List<Error>? = null,

    @field:JsonProperty("tip")
    val tip: TipResponseObject? = null

) : IResponse
