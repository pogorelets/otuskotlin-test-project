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
 * @param code 
 * @param group 
 * @param `field` 
 * @param message 
 */
@Serializable

data class Error (
    @SerialName(value = "code") val code: kotlin.String? = null
,
    @SerialName(value = "group") val group: kotlin.String? = null
,
    @SerialName(value = "field") val `field`: kotlin.String? = null
,
    @SerialName(value = "message") val message: kotlin.String? = null

)
