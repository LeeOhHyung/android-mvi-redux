/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Flowable
import io.reactivex.Single
import kr.ohyung.domain.entity.LegalName
import kr.ohyung.domain.entity.isKoreaLatLng
import kr.ohyung.domain.exception.InvalidLatLonException
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.repository.WeatherRepository
import kr.ohyung.domain.usecase.base.FlowableUseCase
import javax.inject.Inject

class GetCurrentLegalNameUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val executorProvider: ExecutorProvider
) : FlowableUseCase<LegalName>(executorProvider.io(), executorProvider.mainThread()) {

    override fun buildUseCaseFlowable(): Flowable<LegalName> =
        locationRepository.getLocationFromGps()
            .flatMapSingle { location ->
                if(location.isKoreaLatLng())
                    weatherRepository.getCurrentLegalName(location).subscribeOn(executorProvider.io())
                else
                    Single.error(InvalidLatLonException("this location is not korea latitude and longitude"))
            }
}
