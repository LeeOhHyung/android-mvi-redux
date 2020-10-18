/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.data.model

import kr.ohyung.domain.entity.Weather as Entity

data class WeatherDataModel(
    val latitude: Double,
    val longitude: Double,
    val weatherName: String,
    val description: String,
    val icon: String,
    val temp: Double
) : DataModel

fun WeatherDataModel.toEntity() = Entity(
    latitude = latitude,
    longitude = longitude,
    name = weatherName,
    description = description,
    icon = icon,
    temp = temp
)
