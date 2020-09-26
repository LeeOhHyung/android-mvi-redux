/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.domain.entity.SearchHistory

class SearchHistoryEntityMapper : EntityMapper<SearchHistoryDataModel, SearchHistory> {
    override fun toEntity(dataModel: SearchHistoryDataModel) =
        SearchHistory(
            id = dataModel.id,
            keyword = dataModel.keyword,
            timestamp = dataModel.timestamp
        )

    override fun toDataModel(entity: SearchHistory) =
        SearchHistoryDataModel(
            id = entity.id,
            keyword = entity.keyword,
            timestamp = entity.timestamp
        )
}
