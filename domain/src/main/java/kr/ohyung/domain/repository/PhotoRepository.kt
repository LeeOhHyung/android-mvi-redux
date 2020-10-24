/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.repository

import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.PhotoSummary
import kr.ohyung.domain.entity.unit.OrderBy

interface PhotoRepository : Repository {
    fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy): Single<List<PhotoSummary>>
    fun getRandomPhoto(query: String): Single<PhotoSummary>
    fun searchPhotos(query: String, page: Int?, perPage: Int?): Single<List<PhotoSummary>>
}
