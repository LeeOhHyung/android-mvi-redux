package kr.ohyung.local.mock

import kr.ohyung.data.model.BookmarkDataModel
import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.local.model.BookmarkRoomObject
import kr.ohyung.local.model.SearchHistoryRoomObject

object MockLocalData {

    val bookmarkRoomObject = BookmarkRoomObject(
        id = "hello_world",
        description = "Local Layer 테스트 코드에 사용될 Object",
        thumbnail = "N/A",
        likes = 10224,
        username = "이오형"
    )
    val bookmarkDataModel = BookmarkDataModel(
        id = "hello_world",
        description = "Local Layer 테스트 코드에 사용될 Object",
        thumbnail = "N/A",
        likes = 10224,
        username = "이오형"
    )
    val searchHistoryRoomObject = SearchHistoryRoomObject(
        id = 1,
        keyword = "이오형과 함께하는 즐거운 안드로이드 프로그래밍",
        timestamp = 12342312
    )
    val searchHistoryDataModel = SearchHistoryDataModel(
        id = 1,
        keyword = "이오형과 함께하는 즐거운 안드로이드 프로그래밍",
        timestamp = 12342312
    )

    val bookmarkRoomObjects = listOf(bookmarkRoomObject)
    val bookmarkDataModels = listOf(bookmarkDataModel)
    val searchHistoryRoomObjects = listOf(searchHistoryRoomObject)
    val searchHistoryDataModels = listOf(searchHistoryDataModel)
}
