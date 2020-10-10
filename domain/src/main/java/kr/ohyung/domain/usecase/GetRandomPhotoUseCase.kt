/*
 * Created by Lee Oh Hyung on 2020/10/09.
 */
package kr.ohyung.domain.usecase

import io.reactivex.Scheduler
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.repository.PhotoRepository
import kr.ohyung.domain.usecase.base.ParameterizedSingleUseCase
import javax.inject.Inject

class GetRandomPhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    executorThread: Scheduler,
    postExecutionThread: Scheduler
) : ParameterizedSingleUseCase<PhotoSummary, String>(executorThread, postExecutionThread) {
    override fun buildUseCaseSingle(params: String) =
        photoRepository.getRandomPhoto(query = params)
}
