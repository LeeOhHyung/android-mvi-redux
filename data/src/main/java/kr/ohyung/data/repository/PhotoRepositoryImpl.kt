/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.repository

import io.reactivex.Single
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.entity.unit.OrderBy
import kr.ohyung.domain.exception.Externals
import kr.ohyung.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
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

    override fun getRandomPhoto(query: String): Single<PhotoSummary> =
        photoRemoteDataSource.getRandomPhoto(query = query)
            .map { photoDataModel -> photoEntityMapper.toEntity(photoDataModel) }
            .onErrorResumeNext { throwable ->
                val errorType = when(throwable) {
                    is NetworkException.BadRequestException -> Externals.BadRequestException(throwable.message) // 400
                    is NetworkException.UnauthorizedException -> Externals.UnauthorizedException(throwable.message) // 401
                    is NetworkException.NotFoundException -> Externals.NotFoundException(throwable.message) // 404
                    else -> Exception(throwable) // etc
                }
                Single.error(errorType)
            }

    override fun searchPhotos(query: String, page: Int?, perPage: Int?): Single<List<PhotoSummary>> =
        photoRemoteDataSource.searchPhotos(query, page, perPage)
            .map { photoDatamodels -> photoEntityMapper.toEntities(photoDatamodels) }
            .onErrorResumeNext { throwable ->
                val errorType = when(throwable) {
                    is NetworkException.BadRequestException -> Externals.BadRequestException(throwable.message) // 400
                    is NetworkException.UnauthorizedException -> Externals.UnauthorizedException(throwable.message) // 401
                    is NetworkException.NotFoundException -> Externals.NotFoundException(throwable.message) // 404
                    else -> Exception(throwable) // etc
                }
                Single.error(errorType)
            }
}
