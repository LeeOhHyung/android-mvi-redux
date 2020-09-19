/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.source.remote

import io.reactivex.Single
import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.data.source.DataSource

interface PhotoRemoteDataSource : DataSource {
    fun getPhoto(): Single<List<PhotoSummaryDataModel>>
}
