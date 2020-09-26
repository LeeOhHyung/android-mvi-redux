/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.BookmarksDataModel
import kr.ohyung.domain.entity.Bookmarks

class BookmarksEntityMapper : EntityMapper<BookmarksDataModel, Bookmarks> {
    override fun toEntity(dataModel: BookmarksDataModel) =
        Bookmarks(
            id = dataModel.id,
            description = dataModel.description,
            thumbnail = dataModel.thumbnail,
            likes = dataModel.likes,
            username = dataModel.username
        )

    override fun toDataModel(entity: Bookmarks) =
        BookmarksDataModel(
            id = entity.id,
            description = entity.description,
            thumbnail = entity.thumbnail,
            likes = entity.likes,
            username = entity.username
        )
}
