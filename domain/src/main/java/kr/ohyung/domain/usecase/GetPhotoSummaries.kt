/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Scheduler
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.entity.unit.OrderBy
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.usecase.base.ParameterizedSingleUseCase

data class PhotoParams(
    val page: Int = 1, // default = 1
    val perPage: Int = 10, // default = 10
    val orderBy: OrderBy = OrderBy.LATEST // default = latest
)

class GetPhotoSummaries(
    private val photoRepository: PhotoRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedSingleUseCase<List<PhotoSummary>, PhotoParams>(executorThread, postExecutionThread) {
    override fun buildUseCaseSingle(params: PhotoParams) =
        photoRepository.getPhotos(
            page = params.page,
            perPage = params.perPage,
            orderBy = params.orderBy
        )
}
