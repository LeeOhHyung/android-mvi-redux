/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.data.source.remote

import io.reactivex.Single
import kr.ohyung.data.model.LegalNameDataModel
import kr.ohyung.data.model.WeatherDataModel
import kr.ohyung.data.source.DataSource

interface WeatherRemoteDataSource : DataSource {
    fun getCurrentLegalName(latitude: Double, longitude: Double, output: String): Single<LegalNameDataModel>
    fun getWeatherByLatLon(latitude: Double, longitude: Double, units: String): Single<WeatherDataModel>
}
