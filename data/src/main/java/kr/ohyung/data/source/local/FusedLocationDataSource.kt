/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.data.source.local

import io.reactivex.Flowable
import io.reactivex.Single
import kr.ohyung.data.model.LocationDataModel
import kr.ohyung.data.source.DataSource

interface FusedLocationDataSource : DataSource {
    fun getLocation(): Flowable<LocationDataModel>
}
