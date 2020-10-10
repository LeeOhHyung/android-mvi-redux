/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.source

import io.reactivex.Single
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.remote.api.PhotosApi
import kr.ohyung.remote.compose
import kr.ohyung.remote.mapper.PhotosResponseMapper
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val photosApi: PhotosApi,
    private val photosResponseMapper: PhotosResponseMapper
) : PhotoRemoteDataSource {
    override fun getPhotos(page: Int?, perPage: Int?, orderBy: String?): Single<List<PhotoSummaryDataModel>> =
        photosApi.getPhotos(page, perPage, orderBy)
            .map { response -> photosResponseMapper.toDataModels(response) }
            .compose()

    override fun getRandomPhoto(query: String?): Single<PhotoSummaryDataModel> =
        photosApi.getRandomPhoto(query)
            .map { response -> photosResponseMapper.toDataModel(response) }
            .compose()
}
