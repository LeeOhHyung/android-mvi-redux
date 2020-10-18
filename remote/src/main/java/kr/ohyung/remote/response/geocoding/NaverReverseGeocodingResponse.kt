/*
 * Created by Lee Oh Hyoung on 2020/10/18 ..
 */
package kr.ohyung.remote.response.geocoding

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.data.model.LegalNameDataModel
import kr.ohyung.remote.Response

@JsonClass(generateAdapter = true)
data class NaverReverseGeocodingResponse(

    @field:Json(name = "status")
    val status: NaverReverseGeocodingStatus,

    @field:Json(name ="results")
    val results: List<NaverReverseGeocodingResult>
) : Response

fun NaverReverseGeocodingResponse.toDataModel(): LegalNameDataModel =
    results.firstOrNull { it.name == AddressType.LEGAL.value }
        ?.let { result ->
            LegalNameDataModel(
                latitude = result.region.area3.coords.center.latitude,
                longitude = result.region.area3.coords.center.longitude,
                countryCode = result.region.area0.name,
                city = String.format("%s %s %s", result.region.area1.name, result.region.area2.name, result.region.area3.name)
            )
        }
        ?: throw NetworkException.NotFoundException("legalName of Location cannot found")
