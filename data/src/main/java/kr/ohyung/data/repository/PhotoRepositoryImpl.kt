/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.repository

import kr.ohyung.data.mapper.PhotoEntityMapper
import kr.ohyung.data.source.remote.PhotoRemoteDataSource
import kr.ohyung.domain.entity.OrderBy
import kr.ohyung.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoEntityMapper: PhotoEntityMapper
) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int, orderBy: OrderBy) =
        photoRemoteDataSource.getPhotos(page = page, perPage = perPage, orderBy = orderBy)
            .map { photoEntityMapper.toEntities(it) }
}
