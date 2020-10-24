/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.source.remote

import io.reactivex.Single
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.data.source.DataSource

interface PhotoRemoteDataSource : DataSource {
    fun getPhotos(page: Int?, perPage: Int?, orderBy: String?): Single<List<PhotoSummaryDataModel>>
    fun getRandomPhoto(query: String?): Single<PhotoSummaryDataModel>
    fun searchPhotos(query: String, page: Int?, perPage: Int?): Single<List<PhotoSummaryDataModel>>
}
