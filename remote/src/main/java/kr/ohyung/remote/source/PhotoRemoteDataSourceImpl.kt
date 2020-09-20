/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.source

import io.reactivex.Single
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.mapper.PhotosResponseMapper
import retrofit2.HttpException

class PhotoRemoteDataSourceImpl(
    private val photosApi: PhotosApi,
    private val photosResponseMapper: PhotosResponseMapper
) : PhotoRemoteDataSource {
    override fun getPhotos(page: Int, perPage: Int, orderBy: String): Single<List<PhotoSummaryDataModel>> =
        photosApi.getPhotos(page, perPage, orderBy)
            .map { response -> photosResponseMapper.toDataModels(response) }
            .onErrorResumeNext { throwable ->
                when(throwable) {
                    is HttpException -> Single.error(NetworkException.BadRequestException(throwable.message.toString()))
                    else -> Single.error(throwable)
                }
            }
}
