package kr.ohyung.data.mock

import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.domain.entity.PhotoSummary

object MockData {
    val photoSummaryDataModel =
        PhotoSummaryDataModel(
            id = "Photo",
            width = 1000,
            height = 1000,
            color = "컬러 없음",
            description = "설명없음",
            thumbnail = "썸네일 없음",
            likes = 10224,
            username = "이오형"
        )

    val photoSummaryDataModels: List<PhotoSummaryDataModel> =
        listOf(photoSummaryDataModel)

    val photoSummaryEntity =
        PhotoSummary(
            id = "Photo",
            width = 1000,
            height = 1000,
            color = "컬러 없음",
            description = "설명없음",
            thumbnail = "썸네일 없음",
            likes = 10224,
            username = "이오형"
        )

    val photoSummaryEntities =
        listOf(photoSummaryEntity)
}
