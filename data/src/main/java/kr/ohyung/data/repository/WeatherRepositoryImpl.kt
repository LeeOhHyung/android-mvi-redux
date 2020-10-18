/*
 * Created by Lee Oh Hyung on 2020/10/18.
 */
package kr.ohyung.data.repository

import io.reactivex.Single
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.model.toEntity
import kr.ohyung.data.source.remote.WeatherRemoteDataSource
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.Location
import kr.ohyung.domain.entity.Weather
import kr.ohyung.domain.entity.unit.OutputUnit
import kr.ohyung.domain.entity.unit.TempUnit
import kr.ohyung.domain.exception.CurrentLocationNotFoundException
import kr.ohyung.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getCurrentLegalName(location: Location): Single<LegalName> =
        weatherRemoteDataSource
            .getCurrentLegalName(latitude = location.latitude, longitude = location.longitude, output = OutputUnit.JSON.value)
            .map { it.toEntity() }
            .onErrorResumeNext { throwable ->
                when(throwable) {
                    is NetworkException.NotFoundException -> Single.error(CurrentLocationNotFoundException(throwable.message))
                    else -> Single.error(throwable)
                }
            }

    override fun getWeatherByLatLon(location: Location): Single<Weather> =
        weatherRemoteDataSource
            .getWeatherByLatLon(latitude = location.latitude, longitude = location.longitude, units = TempUnit.METRIC.value)
            .map { it.toEntity() }
}
