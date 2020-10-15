package kr.ohyung.remote.mock

import kr.ohyung.data.model.PhotoSummaryDataModel
import kr.ohyung.remote.response.PhotoLinksResponse
import kr.ohyung.remote.response.PhotosResponse
import kr.ohyung.remote.response.UrlsResponse
import kr.ohyung.remote.response.UserResponse

internal object MockData {

    val photoSummaryDataModel =
        PhotoSummaryDataModel(
            id = "Photo",
            width = 1000,
            height = 1000,
            color = "컬러 없음",
            description = "설명없음",
            thumbnail = "썸네일 없음",
            fullSizeImageUrl = "N/A",
            regularSizeImageUrl = "N/A",
            likes = 10224,
            username = "이오형"
        )

    val photoSummaryDataModels: List<PhotoSummaryDataModel> =
        listOf(photoSummaryDataModel)

    val getPhotosResponse =
        PhotosResponse(
            id = "Photo",
            width = 1000,
            height = 1000,
            color = "컬러 없음",
            description = "설명없음",
            alt_description = "대체 설명",
            urls = UrlsResponse(
                raw = null,
                full = "N/A",
                regular = "N/A",
                small = null,
                thumb = "썸네일 없음"
            ),
            links = PhotoLinksResponse(
                self = null,
                html = null,
                download = null,
                download_location = null
            ),
            likes = 10224,
            user = UserResponse(
                id = "",
                username = "이오형",
                portfolio_url = "https://github.com/LeeOhHyung",
                bio = null,
                location = "서울",
                links = null,
                profile_image = UserResponse.UserProfileImageResponse(
                    small = null,
                    medium = null,
                    large = null
                ),
                instagram_username = "안알려줌",
                total_collections = 0,
                total_likes = 10224,
                total_photos = 0,
                accepted_tos = false
            )
        )

    val getPhotosResponses: List<PhotosResponse> =
        listOf(getPhotosResponse)
}
