/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.remote.api

import io.reactivex.Single
import kr.ohyung.remote.Api
import kr.ohyung.remote.BuildConfig
import kr.ohyung.remote.response.CurrentLocationWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi : Api {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY_OPEN_WEATHER_MAP
    ): Single<CurrentLocationWeatherResponse>
}
