/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.source

import io.reactivex.Single
import kr.ohyung.data.model.PhotoSummaryEntity

interface PhotoRemoteDataSource : DataSource {
    fun getPhoto(): Single<List<PhotoSummaryEntity>>
}
