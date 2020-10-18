/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import kr.ohyung.domain.entity.Location
import kr.ohyung.domain.repository.LocationRepository
import kr.ohyung.domain.usecase.base.SingleUseCase

class GetGpsLocationUseCase(
    private val locationRepository: LocationRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : SingleUseCase<Location>(executorThread, postExecutionThread) {

    override fun buildUseCaseSingle(): Single<Location> =
        locationRepository.getLocationFromGps()
            .singleOrError()
}
