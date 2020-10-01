/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.BookmarkDataModel
import kr.ohyung.domain.entity.Bookmark

class BookmarkEntityMapper : EntityMapper<BookmarkDataModel, Bookmark> {
    override fun toEntity(dataModel: BookmarkDataModel) =
        Bookmark(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )

    override fun toDataModel(entity: Bookmark) =
        BookmarkDataModel(
            id = entity.id,
            description = entity.description,
            thumbnail = entity.thumbnail,
            likes = entity.likes,
            username = entity.username
        )
}
