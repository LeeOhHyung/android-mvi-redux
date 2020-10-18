/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.domain.repository

import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.Location
import kr.ohyung.domain.entity.Weather

interface WeatherRepository : Repository {
    fun getCurrentLegalName(location: Location): Single<LegalName>
    fun getWeatherByLatLon(location: Location): Single<Weather>
}
