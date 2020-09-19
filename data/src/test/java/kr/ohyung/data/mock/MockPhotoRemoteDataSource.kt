/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mock

import io.reactivex.Single
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.entity.OrderBy

class MockPhotoRemoteDataSource : PhotoRemoteDataSource {
    override fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy) =
        Single.just(MockData.photoSummaryDataModels)
}
