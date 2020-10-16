/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.mapper

import kr.ohyung.data.model.BookmarkDataModel
import kr.ohyung.local.RoomObjectMapper
import kr.ohyung.local.model.BookmarkRoomObject
import javax.inject.Inject

class BookmarkMapper @Inject constructor() : RoomObjectMapper<BookmarkRoomObject, BookmarkDataModel> {

    override fun toDataModel(roomObject: BookmarkRoomObject) =
        BookmarkDataModel(
            id = roomObject.id,
            description = roomObject.description,
            thumbnail = roomObject.thumbnail,
            likes = roomObject.likes,
            username = roomObject.username
        )

    override fun toRoomObject(dataModel: BookmarkDataModel) =
        BookmarkRoomObject(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )
}
