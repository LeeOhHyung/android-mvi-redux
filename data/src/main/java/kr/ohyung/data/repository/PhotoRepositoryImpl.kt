/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.repository

import io.reactivex.Single
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.entity.OrderBy
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.exception.Externals
import kr.ohyung.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoEntityMapper: PhotoEntityMapper
) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy): Single<List<PhotoSummary>> =
        photoRemoteDataSource.getPhotos(page = page, perPage = perPage, orderBy = orderBy.value)
            .map { photoEntityMapper.toEntities(it) }
            .onErrorResumeNext { throwable ->
                val errorType = when(throwable) {
                    is NetworkException.BadRequestException -> Externals.BadRequestException(throwable.message)
                    else -> Exception(throwable)
                }
                Single.error(errorType)
            }
}
