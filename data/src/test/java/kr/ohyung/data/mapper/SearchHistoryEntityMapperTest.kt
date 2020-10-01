/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.mock.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchHistoryEntityMapperTest {

    private lateinit var searchHistoryEntityMapper: SearchHistoryEntityMapper

    @Before
    fun setup() {
        searchHistoryEntityMapper = SearchHistoryEntityMapper()
    }

    @Test
    fun `SearchHistoryEntityMapper toEntity 동작 테스트`() {
        val searchHistoryDataModel = MockData.searchHistoryDataModel
        val searchHistoryEntity = searchHistoryEntityMapper.toEntity(dataModel = searchHistoryDataModel)
        assertEquals(MockData.searchHistoryEntity.id, searchHistoryEntity.id)
        assertEquals(MockData.searchHistoryEntity.keyword, searchHistoryEntity.keyword)
        assertEquals(MockData.searchHistoryEntity.timestamp, searchHistoryEntity.timestamp)
    }

    @Test
    fun `SearchHistoryEntityMapper toDataModel 동작 테스트`() {
        val searchHistoryEntity = MockData.searchHistoryEntity
        val searchHistoryDataModel = searchHistoryEntityMapper.toDataModel(entity = searchHistoryEntity)
        assertEquals(MockData.searchHistoryDataModel.id, searchHistoryDataModel.id)
        assertEquals(MockData.searchHistoryDataModel.keyword, searchHistoryDataModel.keyword)
        assertEquals(MockData.searchHistoryDataModel.timestamp, searchHistoryDataModel.timestamp)
    }

    @Test
    fun `SearchHistoryEntityMapper toEntities 동작 테스트`() {
        val searchHistoryDataModels = MockData.searchHistoryDataModels
        val searchHistoryEntities = searchHistoryEntityMapper.toEntities(dataModels = searchHistoryDataModels)
        assertEquals(MockData.searchHistoryEntities.first().id, searchHistoryEntities.first().id)
        assertEquals(MockData.searchHistoryEntities.first().keyword, searchHistoryEntities.first().keyword)
        assertEquals(MockData.searchHistoryEntities.first().timestamp, searchHistoryEntities.first().timestamp)
    }

    @Test
    fun `SearchHistoryEntityMapper toDataModels 동작 테스트`() {
        val searchHistoryEntities = MockData.searchHistoryEntities
        val searchHistoryDataModels = searchHistoryEntityMapper.toDataModels(entities = searchHistoryEntities)
        assertEquals(MockData.searchHistoryDataModels.first().id, searchHistoryDataModels.first().id)
        assertEquals(MockData.searchHistoryDataModels.first().keyword, searchHistoryDataModels.first().keyword)
        assertEquals(MockData.searchHistoryDataModels.first().timestamp, searchHistoryDataModels.first().timestamp)
    }
}
