/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.mapper

import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.remote.response.PhotosResponse

class PhotosResponseMapper : ResponseMapper<PhotosResponse, PhotoSummaryDataModel> {
    override fun toDataModel(response: PhotosResponse) =
        PhotoSummaryDataModel(
            id = response.id,
            width = response.width,
            height = response.height,
            color = response.color,
            description = response.description,
            thumbnail = response.thumbnail,
            likes = response.likes,
            username = response.username
        )
}
