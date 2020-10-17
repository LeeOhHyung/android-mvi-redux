/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.domain.repository

import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.Weather

interface WeatherRepository : Repository {
    fun getCurrentLegalName(lat: Double, lon: Double): Single<LegalName>
    fun getWeatherByLatLon(lat: Double, lon: Double): Single<Weather>
}
