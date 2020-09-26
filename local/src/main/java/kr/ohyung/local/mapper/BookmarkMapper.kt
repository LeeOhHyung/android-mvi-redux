/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.mapper

import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.local.RoomObjectMapper
import kr.ohyung.local.model.BookmarkRoomObject

class BookmarkMapper : RoomObjectMapper<BookmarkRoomObject, BookmarksDataModel> {

    override fun toDataModel(roomObject: BookmarkRoomObject) =
        BookmarksDataModel(
            id = roomObject.id,
            description = roomObject.description,
            thumbnail = roomObject.thumbnail,
            likes = roomObject.likes,
            username = roomObject.username
        )

    override fun toRoomObject(dataModel: BookmarksDataModel) =
        BookmarkRoomObject(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )
}
