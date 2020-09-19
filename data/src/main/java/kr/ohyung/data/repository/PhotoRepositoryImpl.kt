/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.repository

import kr.ohyung.data.model.toEntity
import kr.ohyung.data.source.PhotoRemoteDataSource
import kr.ohyung.domain.entity.OrderBy
import kr.ohyung.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy) =
        photoRemoteDataSource.getPhoto().map { it.toEntity() }
}
