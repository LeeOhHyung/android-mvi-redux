package kr.ohyung.local.mock

import kr.ohyung.data.model.BookmarkDataModel
import kr.ohyung.local.model.BookmarkRoomObject

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

    val bookmarkRoomObjects = listOf(bookmarkRoomObject)
    val bookmarkDataModels = listOf(bookmarkDataModel)
}
