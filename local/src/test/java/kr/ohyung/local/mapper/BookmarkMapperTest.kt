/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.local.mapper

import kr.ohyung.local.mock.MockLocalData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookmarkMapperTest {

    private lateinit var bookmarkMapper: BookmarkMapper

    @Before
    fun setup() {
        bookmarkMapper = BookmarkMapper()
    }

    @Test
    fun `BookmarkMapper toDataModel Test`() {
        val bookmarkRoomObject = MockLocalData.bookmarkRoomObject
        val bookmarkDataModel = bookmarkMapper.toDataModel(roomObject = bookmarkRoomObject)

        // BookmarkMapper 를 통해 변환한 dataModel 과 실제 dataModel 같은지 비교
        assertEquals(expected = MockLocalData.bookmarkDataModel.id, actual = bookmarkDataModel.id)
        assertEquals(expected = MockLocalData.bookmarkDataModel.description, actual = bookmarkDataModel.description)
        assertEquals(expected = MockLocalData.bookmarkDataModel.thumbnail, actual = bookmarkDataModel.thumbnail)
        assertEquals(expected = MockLocalData.bookmarkDataModel.likes, actual = bookmarkDataModel.likes)
        assertEquals(expected = MockLocalData.bookmarkDataModel.username, actual = bookmarkDataModel.username)
    }

    @Test
    fun `BookmarkMapper toRoomObject Test`() {
        val bookmarkDataModel = MockLocalData.bookmarkDataModel
        val bookmarkRoomObject = bookmarkMapper.toRoomObject(dataModel = bookmarkDataModel)

        // BookmarkMapper 를 통해 변환한 RoomObject 와 실제 RoomObject 같은지 비교
        assertEquals(expected = MockLocalData.bookmarkRoomObject.id, actual = bookmarkRoomObject.id)
        assertEquals(expected = MockLocalData.bookmarkRoomObject.description, actual = bookmarkRoomObject.description)
        assertEquals(expected = MockLocalData.bookmarkRoomObject.thumbnail, actual = bookmarkRoomObject.thumbnail)
        assertEquals(expected = MockLocalData.bookmarkRoomObject.likes, actual = bookmarkRoomObject.likes)
        assertEquals(expected = MockLocalData.bookmarkRoomObject.username, actual = bookmarkRoomObject.username)
    }

    @Test
    fun `BookmarkMapper toDataModels Test`() {
        val bookmarkRoomObjects = MockLocalData.bookmarkRoomObjects
        val bookmarkDataModels = bookmarkMapper.toDataModels(roomObjects = bookmarkRoomObjects)

        // BookmarkMapper 를 통해 변환한 리스트와 실제 리스트가 같은지 비교
        assertEquals(expected = MockLocalData.bookmarkDataModels.first().id, actual = bookmarkDataModels.first().id)
        assertEquals(expected = MockLocalData.bookmarkDataModels.first().description, actual = bookmarkDataModels.first().description)
        assertEquals(expected = MockLocalData.bookmarkDataModels.first().thumbnail, actual = bookmarkDataModels.first().thumbnail)
        assertEquals(expected = MockLocalData.bookmarkDataModels.first().likes, actual = bookmarkDataModels.first().likes)
        assertEquals(expected = MockLocalData.bookmarkDataModels.first().username, actual = bookmarkDataModels.first().username)
    }

    @Test
    fun `BookmarkMapper toRoomObjects Test`() {
        val bookmarkDataModels = MockLocalData.bookmarkDataModels
        val bookmarkRoomObjects = bookmarkMapper.toRoomObjects(dataModels = bookmarkDataModels)

        // BookmarkMapper 를 통해 변환한 RoomObjects 리스트와 실제 RoomObjects 리스트가 같은지 비교
        assertEquals(expected = MockLocalData.bookmarkRoomObjects.first().id, actual = bookmarkRoomObjects.first().id)
        assertEquals(expected = MockLocalData.bookmarkRoomObjects.first().description, actual = bookmarkRoomObjects.first().description)
        assertEquals(expected = MockLocalData.bookmarkRoomObjects.first().thumbnail, actual = bookmarkRoomObjects.first().thumbnail)
        assertEquals(expected = MockLocalData.bookmarkRoomObjects.first().likes, actual = bookmarkRoomObjects.first().likes)
        assertEquals(expected = MockLocalData.bookmarkRoomObjects.first().username, actual = bookmarkRoomObjects.first().username)
    }
}
