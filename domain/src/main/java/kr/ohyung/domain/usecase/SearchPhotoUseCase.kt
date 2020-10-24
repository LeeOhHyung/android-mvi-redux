/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Single
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.entity.unit.OrderBy
import kr.ohyung.domain.executor.ExecutorProvider
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.usecase.base.ParameterizedSingleUseCase
import javax.inject.Inject

data class SearchParams(
    val query: String,
    val page: Int = 1, // default = 1
    val perPage: Int = 20 // default = 10
)

class SearchPhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    executorProvider: ExecutorProvider
) : ParameterizedSingleUseCase<List<PhotoSummary>, SearchParams>(executorProvider.io(), executorProvider.mainThread()) {

    override fun buildUseCaseSingle(params: SearchParams): Single<List<PhotoSummary>> =
        photoRepository.searchPhotos(
            query = params.query,
            page = params.page,
            perPage = params.perPage
        )
}
