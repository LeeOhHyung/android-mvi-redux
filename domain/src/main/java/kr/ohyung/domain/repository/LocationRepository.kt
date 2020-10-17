/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.domain.repository

import io.reactivex.Single
import kr.ohyung.domain.Repository
import kr.ohyung.domain.entity.Location

interface LocationRepository : Repository {
    fun getLocationFromGps(): Single<Location>
}
