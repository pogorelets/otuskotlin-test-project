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

import ru.otus.otuskotlin.api.v1.models.TipSearchFilter

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 *
 * @param tipFilter 
 */


data class TipSearchRequestAllOf (

    @field:JsonProperty("tipFilter")
    val tipFilter: TipSearchFilter? = null

)
