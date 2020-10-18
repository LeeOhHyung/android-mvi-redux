/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.isKoreaLatLng
import kr.ohyung.domain.exception.InvalidLatLonException
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.repository.WeatherRepository
import kr.ohyung.domain.usecase.base.SingleUseCase

class GetCurrentLegalNameUseCase(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : SingleUseCase<LegalName>(executorThread, postExecutionThread) {

    override fun buildUseCaseSingle(): Single<LegalName> =
        locationRepository.getLocationFromGps()
            .flatMap { location ->
                if(location.isKoreaLatLng())
                    weatherRepository.getCurrentLegalName(location)
                else
                    Single.error(InvalidLatLonException("this location is not korea latitude and longitude"))
            }
}
