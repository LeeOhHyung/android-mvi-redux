/*
 * Created by Lee Oh Hyung on 2020/09/21.
 */
package kr.ohyung.remote.mapper

import kr.ohyung.remote.mock.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class PhotosResponseMapperTest {

    private lateinit var photosResponseMapper: PhotosResponseMapper

    @Before
    fun setup() {
        photosResponseMapper = PhotosResponseMapper()
    }

    @Test
    fun `PhotosResponseMapper toDataModel Test`() {
        val getPhotosResponse = MockData.getPhotosResponse
        val photoSummaryDataModel = photosResponseMapper.toDataModel(response = getPhotosResponse)
        assertEquals(MockData.photoSummaryDataModel.id, photoSummaryDataModel.id)
        assertEquals(MockData.photoSummaryDataModel.width, photoSummaryDataModel.height)
        assertEquals(MockData.photoSummaryDataModel.color, photoSummaryDataModel.color)
        assertEquals(MockData.photoSummaryDataModel.description, photoSummaryDataModel.description)
        assertEquals(MockData.photoSummaryDataModel.thumbnail, photoSummaryDataModel.thumbnail)
        assertEquals(MockData.photoSummaryDataModel.likes, photoSummaryDataModel.likes)
        assertEquals(MockData.photoSummaryDataModel.username, photoSummaryDataModel.username)
    }

    @Test
    fun `PhotoResponseMapper toDataModels Test`() {
        val getPhotosResponses = MockData.getPhotosResponses
        val photoSummaryDataModels = photosResponseMapper.toDataModels(responses = getPhotosResponses)

        // MockData 에 선언한 DataModels 의 첫번째 아이템(기대값)과, Mapper 를 통해서 변환된 리스트의 첫번째 아이템(실제값)이 같은지 확인
        assertEquals(MockData.photoSummaryDataModels.first(), photoSummaryDataModels.first())
        assertEquals(MockData.photoSummaryDataModels.first().id, photoSummaryDataModels.first().id)
        assertEquals(MockData.photoSummaryDataModels.first().width, photoSummaryDataModels.first().width)
        assertEquals(MockData.photoSummaryDataModels.first().color, photoSummaryDataModels.first().color)
        assertEquals(MockData.photoSummaryDataModels.first().description, photoSummaryDataModels.first().description)
        assertEquals(MockData.photoSummaryDataModels.first().thumbnail, photoSummaryDataModels.first().thumbnail)
        assertEquals(MockData.photoSummaryDataModels.first().likes, photoSummaryDataModels.first().likes)
        assertEquals(MockData.photoSummaryDataModels.first().username, photoSummaryDataModels.first().username)

        assertNotEquals(MockData.photoSummaryDataModels.first(), photoSummaryDataModels.first().copy(id = "다른 Id"))
        assertNotEquals(MockData.photoSummaryDataModels.first(), photoSummaryDataModels.first().copy(username = "다른 사람"))
    }
}
