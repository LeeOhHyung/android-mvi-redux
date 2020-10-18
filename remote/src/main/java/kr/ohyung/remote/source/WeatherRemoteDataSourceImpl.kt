/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.remote.source

import io.reactivex.Single
import kr.ohyung.data.model.LegalNameDataModel
import kr.ohyung.data.model.WeatherDataModel
import kr.ohyung.data.source.remote.WeatherRemoteDataSource
import kr.ohyung.remote.api.ReverseGeocodingApi
import kr.ohyung.remote.api.WeatherApi
import kr.ohyung.remote.compose
import kr.ohyung.remote.response.geocoding.toDataModel
import kr.ohyung.remote.response.toDataModel
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val reverseGeocodingApi: ReverseGeocodingApi,
    private val weatherApi: WeatherApi
) : WeatherRemoteDataSource {

    override fun getCurrentLegalName(latitude: Double, longitude: Double, output: String): Single<LegalNameDataModel> =
        reverseGeocodingApi.getLegalNameByLatLon(concatLatLonToCoords(latitude, longitude), output)
            .map { response -> response.toDataModel() }
            .compose()

    override fun getWeatherByLatLon(latitude: Double, longitude: Double, units: String): Single<WeatherDataModel> =
        weatherApi.getCurrentWeather(latitude = latitude, longitude = longitude, units = units)
            .map { it.toDataModel() }
            .compose()

    private fun concatLatLonToCoords(lat: Double, lon: Double): String =
        String.format("%s,%s", lon.toString(), lat.toString())
}
