/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mock

import io.reactivex.Single
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.data.source.remote.PhotoRemoteDataSource

class MockPhotoRemoteDataSource : PhotoRemoteDataSource {
    override fun getPhotos(page: Int?, perPage: Int?, orderBy: String?) =
        Single.just(MockData.photoSummaryDataModels)
}
