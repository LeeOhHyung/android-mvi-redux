/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.domain.entity.Bookmark

class BookmarkEntityMapper : EntityMapper<BookmarksDataModel, Bookmark> {
    override fun toEntity(dataModel: BookmarksDataModel) =
        Bookmark(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )

    override fun toDataModel(entity: Bookmark) =
        BookmarksDataModel(
            id = entity.id,
            description = entity.description,
            thumbnail = entity.thumbnail,
            likes = entity.likes,
            username = entity.username
        )
}
