/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.repository

import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.OrderBy
import kr.ohyung.domain.entity.PhotoSummary

interface PhotoRepository : Repository {
    fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy): Single<List<PhotoSummary>>
}
