/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.domain.entity.PhotoSummary

class PhotoEntityMapper : EntityMapper<PhotoSummaryDataModel, PhotoSummary> {
    override fun toEntity(dataModel: PhotoSummaryDataModel) = with(dataModel) {
        PhotoSummary(
            id = id,
            width = width,
            height = height,
            color = color,
            description = description,
            thumbnail = thumbnail,
            likes = likes,
            username = username
        )
    }

    override fun toDataModel(entity: PhotoSummary) = with(entity) {
        PhotoSummaryDataModel(
            id = id,
            width = width,
            height = height,
            color = color,
            description = description,
            thumbnail = thumbnail,
            likes = likes,
            username = username
        )
    }
}
