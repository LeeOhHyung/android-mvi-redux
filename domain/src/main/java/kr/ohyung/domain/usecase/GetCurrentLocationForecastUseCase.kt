/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import kr.ohyung.domain.entity.*
import kr.ohyung.domain.exception.InvalidLatLonException
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.repository.WeatherRepository
import kr.ohyung.domain.usecase.base.SingleUseCase

class GetCurrentLocationForecastUseCase(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val executorProvider: ExecutorProvider
) : SingleUseCase<Forecast>(executorProvider.io(), executorProvider.mainThread()) {

    override fun buildUseCaseSingle(): Single<Forecast> =
        locationRepository.getLocationFromGps()
            .flatMap { location ->
                if(location.isKoreaLatLng())
                    Single.zip(
                        weatherRepository.getCurrentLegalName(location.latitude, location.longitude).subscribeOn(executorProvider.io()),
                        weatherRepository.getWeatherByLatLon(location.latitude, location.longitude).subscribeOn(executorProvider.io()),
                        BiFunction { legalName: LegalName, weather: Weather ->
                            Forecast(
                                countryCode = legalName.countryCode,
                                city = legalName.city,
                                weather = weather
                            )
                        }
                    )
                else
                    Single.error(InvalidLatLonException("this location is not korea latitude and longitude"))
            }
}
