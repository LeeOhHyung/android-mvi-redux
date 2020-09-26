/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.mapper

import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.local.RoomObjectMapper
import kr.ohyung.local.model.BookmarksRoomObject

class BookmarksMapper : RoomObjectMapper<BookmarksRoomObject, BookmarksDataModel> {

    override fun toDataModel(roomObject: BookmarksRoomObject) =
        BookmarksDataModel(
            id = roomObject.id,
            description = roomObject.description,
            thumbnail = roomObject.thumbnail,
            likes = roomObject.likes,
            username = roomObject.username
        )

    override fun toRoomObject(dataModel: BookmarksDataModel) =
        BookmarksRoomObject(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )
}
