/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.local.source

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
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
    private val locationRequest = LocationRequest.create().apply {
        interval = LOCATION_REQUEST_INTERVAL
        fastestInterval = LOCATION_REQUEST_FASTEST_INTERVAL
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            if(result?.lastLocation != null) {
                setLocation(result.lastLocation)
            }
        }
    }

    override fun getLocation(): Flowable<LocationDataModel> =
        locationSubject.toFlowable(BackpressureStrategy.MISSING)
            .doOnSubscribe { addOnGetLocationUpdateListener() }
            .doOnCancel { removeOnGetLocationUpdateListener() }

    @SuppressLint("MissingPermission")
    private fun addOnGetLocationUpdateListener() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(::setLocation)
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun removeOnGetLocationUpdateListener() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun setLocation(location: Location?) =
        location?.let {
            locationSubject.onNext(
                LocationDataModel(location.latitude, location.longitude)
            )
        }

    companion object {
        // Only use for continuous updates location
        private const val LOCATION_REQUEST_INTERVAL = 3000L
        private const val LOCATION_REQUEST_FASTEST_INTERVAL = 1500L
    }
}
