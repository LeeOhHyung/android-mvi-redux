/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.local.source

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import kr.ohyung.data.model.LocationDataModel
import kr.ohyung.data.source.local.FusedLocationDataSource
import javax.inject.Inject

class FusedLocationDataSourceImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : FusedLocationDataSource {

    private val locationSubject = PublishSubject.create<LocationDataModel>()

    override fun getLocation(): Flowable<LocationDataModel> =
        locationSubject.toFlowable(BackpressureStrategy.MISSING)
            .doOnSubscribe { addOnGetLocationSuccessListener() }

    @SuppressLint("MissingPermission")
    private fun addOnGetLocationSuccessListener() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(::setLocation)
    }

    private fun setLocation(location: Location?) =
        location?.let {
            locationSubject.onNext(
                LocationDataModel(location.latitude, location.longitude)
            )
        }
}
