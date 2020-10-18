/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String,
    val icon: String,
    val temp: Double
): Entity
