/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.data.model

import kr.ohyung.domain.entity.LegalName as Entity

data class LegalNameDataModel(
    val latitude: Double,
    val longitude: Double,
    val countryCode: String,
    val city: String
) : DataModel

fun LegalNameDataModel.toEntity() = Entity(
    latitude = latitude,
    longitude = longitude,
    countryCode = countryCode,
    city = city
)
