/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class Location(
    val latitude: Double,
    val longitude: Double
) : Entity

fun Location.isKoreaLatLng() = (latitude in 32.0..43.0 && longitude in 124.0..132.0)
