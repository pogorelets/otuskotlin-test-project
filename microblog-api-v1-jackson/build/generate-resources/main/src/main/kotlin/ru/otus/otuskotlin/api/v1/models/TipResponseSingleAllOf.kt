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

import ru.otus.otuskotlin.api.v1.models.TipResponseObject

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Ответ с одним объектом комментария
 *
 * @param tip 
 */


data class TipResponseSingleAllOf (

    @field:JsonProperty("tip")
    val tip: TipResponseObject? = null

)

