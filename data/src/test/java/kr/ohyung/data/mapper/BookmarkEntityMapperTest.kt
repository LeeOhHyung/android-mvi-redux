/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.mock.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookmarkEntityMapperTest {

    private lateinit var bookmarkEntityMapper: BookmarkEntityMapper

    @Before
    fun setup() {
        bookmarkEntityMapper = BookmarkEntityMapper()
    }

    @Test
    fun `BookmarkEntityMapper toEntity 동작 테스트`() {
        val bookmarkDataModel = MockData.bookmarkDataModel
        val bookmarkEntity = bookmarkEntityMapper.toEntity(dataModel = bookmarkDataModel)
        assertEquals(MockData.bookmarkEntity.id, bookmarkEntity.id)
        assertEquals(MockData.bookmarkEntity.description, bookmarkEntity.description)
        assertEquals(MockData.bookmarkEntity.thumbnail, bookmarkEntity.thumbnail)
        assertEquals(MockData.bookmarkEntity.likes, bookmarkEntity.likes)
        assertEquals(MockData.bookmarkEntity.username, bookmarkEntity.username)
    }

    @Test
    fun `BookmarkEntityMapper toDataModel 동작 테스트`() {
        val bookmarkEntity = MockData.bookmarkEntity
        val bookmarkDataModel = bookmarkEntityMapper.toDataModel(entity = bookmarkEntity)
        assertEquals(MockData.bookmarkDataModel.id, bookmarkDataModel.id)
        assertEquals(MockData.bookmarkDataModel.description, bookmarkDataModel.description)
        assertEquals(MockData.bookmarkDataModel.thumbnail, bookmarkDataModel.thumbnail)
        assertEquals(MockData.bookmarkDataModel.likes, bookmarkDataModel.likes)
        assertEquals(MockData.bookmarkDataModel.username, bookmarkDataModel.username)
    }

    @Test
    fun `BookmarkEntityMapper toEntities 동작 테스트`() {
        val bookmarkDataModels = MockData.bookmarkDataModels
        val bookmarkEntities = bookmarkEntityMapper.toEntities(dataModels = bookmarkDataModels)

        // Mapper 를 통해서 변환한 Entities 리스트와 실제 리스트가 동일한지 확인
        assertEquals(MockData.bookmarkEntities.first().id, bookmarkEntities.first().id)
        assertEquals(MockData.bookmarkEntities.first().description, bookmarkEntities.first().description)
        assertEquals(MockData.bookmarkEntities.first().thumbnail, bookmarkEntities.first().thumbnail)
        assertEquals(MockData.bookmarkEntities.first().likes, bookmarkEntities.first().likes)
        assertEquals(MockData.bookmarkEntities.first().username, bookmarkEntities.first().username)
    }

    @Test
    fun `BookmarkEntityMapper toDataModels 동작 테스트`() {
        val bookmarkEntities = MockData.bookmarkEntities
        val bookmarkDataModels = bookmarkEntityMapper.toDataModels(entities = bookmarkEntities)

        // Mapper 를 통해서 변환한 리스트와 실제 리스트가 같은지 확인
        assertEquals(MockData.bookmarkDataModels.first().id, bookmarkDataModels.first().id)
        assertEquals(MockData.bookmarkDataModels.first().description, bookmarkDataModels.first().description)
        assertEquals(MockData.bookmarkDataModels.first().thumbnail, bookmarkDataModels.first().thumbnail)
        assertEquals(MockData.bookmarkDataModels.first().likes, bookmarkDataModels.first().likes)
        assertEquals(MockData.bookmarkDataModels.first().username, bookmarkDataModels.first().username)
    }
}
