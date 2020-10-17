/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.data.model

import kr.ohyung.domain.entity.Location

data class LocationDataModel(
    val latitude: Double,
    val longitude: Double
) : DataModel

fun LocationDataModel.toEntity() =
    Location(
        latitude = latitude,
        longitude = longitude
    )
