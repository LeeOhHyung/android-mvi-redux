/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.data.repository

import io.reactivex.Flowable
import kr.ohyung.data.model.toEntity
import kr.ohyung.data.source.local.FusedLocationDataSource
import kr.ohyung.domain.entity.Location
import kr.ohyung.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationDataSource: FusedLocationDataSource
) : LocationRepository {

    override fun getLocationFromGps(): Flowable<Location> =
        fusedLocationDataSource.getLocation()
            .map { it.toEntity() }

}
