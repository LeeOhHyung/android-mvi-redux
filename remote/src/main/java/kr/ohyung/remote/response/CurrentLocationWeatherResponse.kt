/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.data.model.WeatherDataModel
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class CurrentLocationWeatherResponse(
    @field:Json(name = "cod")
    val cod: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "coord")
    val coord: Coord,
    @field:Json(name = "weather")
    val weather: List<Weather>,
    @field:Json(name = "base")
    val base: String,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "main")
    val main: Main
) : Response {

    @JsonClass(generateAdapter = true)
    data class Coord(
        @field:Json(name = "lat")
        val lat: Double,
        @field:Json(name = "lon")
        val lon: Double
    )

    @JsonClass(generateAdapter = true)
    data class Weather(
        @field:Json(name = "id")
        val id: Int,
        @field:Json(name = "main")
        val main: String,
        @field:Json(name = "description")
        val description: String,
        @field:Json(name = "icon")
        val icon: String
    )

    @JsonClass(generateAdapter = true)
    data class Main(
        @field:Json(name = "temp")
        val temp: Double,
        @field:Json(name = "feels_like")
        val feels_like: Double,
        @field:Json(name = "temp_min")
        val temp_min: Double,
        @field:Json(name = "temp_max")
        val temp_max: Double,
        @field:Json(name = "pressure")
        val pressure: Int,
        @field:Json(name = "humidity")
        val humidity: Int
    )
}

fun CurrentLocationWeatherResponse.toDataModel() = WeatherDataModel(
    latitude = coord.lat,
    longitude = coord.lon,
    weatherName = weather.first().main,
    description = weather.first().description,
    icon = weather.first().icon,
    temp = main.temp
)
