/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.local.mapper

import kr.ohyung.local.mock.MockLocalData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchHistoryMapperTest {

    private lateinit var searchHistoryMapper: SearchHistoryMapper

    @Before
    fun setup() {
        searchHistoryMapper = SearchHistoryMapper()
    }

    @Test
    fun `SearchHistoryMapper toDataModel Test`() {
        val searchHistoryRoomObject = MockLocalData.searchHistoryRoomObject
        val mappedDataModel = searchHistoryMapper.toDataModel(roomObject = searchHistoryRoomObject)

        // MockLocalData 에 정의된 DataModel 과 Mapper 통해 변환된 DataModel 의 필드값이 모두 같은지 비교
        assertEquals(expected = MockLocalData.searchHistoryDataModel.id, actual = mappedDataModel.id)
        assertEquals(expected = MockLocalData.searchHistoryDataModel.keyword, actual = mappedDataModel.keyword)
        assertEquals(expected = MockLocalData.searchHistoryDataModel.timestamp, actual = mappedDataModel.timestamp)
    }

    @Test
    fun `SearchHistoryMapper toRoomObject Test`() {
        val searchHistoryDataModel = MockLocalData.searchHistoryDataModel
        val mappedRoomObject = searchHistoryMapper.toRoomObject(dataModel = searchHistoryDataModel)

        // MockLocalData 에 정의된 RoomObject 와 Mapper 를 통해 변환한 RoomObject 가 같은지 비교
        assertEquals(expected = MockLocalData.searchHistoryDataModel.id, actual = mappedRoomObject.id)
        assertEquals(expected = MockLocalData.searchHistoryDataModel.keyword, actual = mappedRoomObject.keyword)
        assertEquals(expected = MockLocalData.searchHistoryDataModel.timestamp, actual = mappedRoomObject.timestamp)
    }

    @Test
    fun `SearchHistoryMapper toDataModels Test`() {
        val searchHistoryRoomObjects = MockLocalData.searchHistoryRoomObjects
        val mappedDataModels = searchHistoryMapper.toDataModels(roomObjects = searchHistoryRoomObjects)

        assertEquals(expected = MockLocalData.searchHistoryDataModels.first().id, actual = mappedDataModels.first().id)
        assertEquals(expected = MockLocalData.searchHistoryDataModels.first().keyword , actual = mappedDataModels.first().keyword)
        assertEquals(expected = MockLocalData.searchHistoryDataModels.first().timestamp, actual = mappedDataModels.first().timestamp)
    }

    @Test
    fun `SearchHistoryMapper toRoomObjects Test`() {
        val searchHistoryDataModels = MockLocalData.searchHistoryDataModels
        val mappedRoomObjects = searchHistoryMapper.toRoomObjects(dataModels = searchHistoryDataModels)

        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().id, mappedRoomObjects.first().id)
        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().keyword, mappedRoomObjects.first().keyword)
        assertEquals(expected = MockLocalData.searchHistoryRoomObjects.first().timestamp, mappedRoomObjects.first().timestamp)
    }

}
