/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.data.repository

import io.reactivex.Single
import kr.ohyung.data.model.toEntity
import kr.ohyung.data.source.local.FusedLocationDataSource
import kr.ohyung.domain.entity.Location
import kr.ohyung.domain.repository.GpsLocationRepository
import javax.inject.Inject

class GpsLocationRepositoryImpl @Inject constructor(
    private val fusedLocationDataSource: FusedLocationDataSource
) : GpsLocationRepository {
    override fun getLocationFromGps(): Single<Location> =
        fusedLocationDataSource.getLocation()
            .map { it.toEntity() }
}
