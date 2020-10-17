/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class Forecast(
    val countryCode: String,
    val city: String,
    val weather: Weather
): Entity
