/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.mock.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PhotoEntityMapperTest {

    private lateinit var photoEntityMapper: PhotoEntityMapper

    @Before
    fun setup() {
        photoEntityMapper = PhotoEntityMapper()
    }

    @Test
    fun `PhotoMapper ToEntity Test`() {
        val photoDataModel = MockData.photoSummaryDataModel
        val photoEntity = photoEntityMapper.toEntity(dataModel = photoDataModel)
        assertEquals(MockData.photoSummaryEntity.id, photoEntity.id)
        assertEquals(MockData.photoSummaryEntity.width, photoEntity.width)
        assertEquals(MockData.photoSummaryEntity.height, photoEntity.height)
        assertEquals(MockData.photoSummaryEntity.color, photoEntity.color)
        assertEquals(MockData.photoSummaryEntity.description, photoEntity.description)
        assertEquals(MockData.photoSummaryEntity.thumbnail, photoEntity.thumbnail)
        assertEquals(MockData.photoSummaryEntity.likes, photoEntity.likes)
        assertEquals(MockData.photoSummaryEntity.username, photoEntity.username)
    }

    @Test
    fun `PhotoMapper toDataModel Test`() {
        val photoEntity = MockData.photoSummaryEntity
        val photoDataModel = photoEntityMapper.toDataModel(entity = photoEntity)
        assertEquals(MockData.photoSummaryDataModel.id, photoDataModel.id)
        assertEquals(MockData.photoSummaryDataModel.width, photoDataModel.width)
        assertEquals(MockData.photoSummaryDataModel.height, photoDataModel.height)
        assertEquals(MockData.photoSummaryDataModel.color, photoDataModel.color)
        assertEquals(MockData.photoSummaryDataModel.description, photoDataModel.description)
        assertEquals(MockData.photoSummaryDataModel.thumbnail, photoDataModel.thumbnail)
        assertEquals(MockData.photoSummaryDataModel.likes, photoDataModel.likes)
        assertEquals(MockData.photoSummaryDataModel.username, photoDataModel.username)
    }

    @Test
    fun `PhotoMapper toEntities Test`() {
        val photoDataModels = MockData.photoSummaryDataModels
        val photoEntities = photoEntityMapper.toEntities(dataModels = photoDataModels) // List<PhotoDataModels>

        // MockData 에 선언한 Entities 의 첫번째 아이템(기대값)과, Mapper 를 통해서 변환된 리스트의 첫번째 아이템(실제값)이 같은지 확인
        assertEquals(MockData.photoSummaryEntities.first(), photoEntities.first())
        assertEquals(MockData.photoSummaryEntities.first().id, photoEntities.first().id)
        assertNotEquals(MockData.photoSummaryEntities.first(), photoEntities.first().copy(id = "이상한 Id"))
        assertNotEquals(MockData.photoSummaryEntities.first(), photoEntities.first().copy(username = "이상한 이름"))
    }

    @Test
    fun `PhotoMapper toDataModels Test`() {
        val photoEntities = MockData.photoSummaryEntities
        val photoDataModels = photoEntityMapper.toDataModels(entities = photoEntities)

        // MockData 에 선언한 DataModels 의 첫번째 아이템(기대값)과, Mapper 를 통해서 변환된 리스트의 첫번째 아이템(실제값)이 같은지 확인
        assertEquals(MockData.photoSummaryDataModels.first(), photoDataModels.first())
        assertEquals(MockData.photoSummaryDataModels.first().id, photoDataModels.first().id)
        assertNotEquals(MockData.photoSummaryDataModels.first(), photoDataModels.first().copy(id = "Chicken"))
        assertNotEquals(MockData.photoSummaryDataModels.first(), photoDataModels.first().copy(id = "Uncle Bob"))
    }
}
