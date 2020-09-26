/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.mapper

import kr.ohyung.data.model.SearchHistoryDataModel
import kr.ohyung.local.RoomObjectMapper
import kr.ohyung.local.model.SearchHistoryRoomObject

class SearchHistoryMapper : RoomObjectMapper<SearchHistoryRoomObject, SearchHistoryDataModel> {

    override fun toDataModel(roomObject: SearchHistoryRoomObject) =
        SearchHistoryDataModel(
            id = roomObject.id,
            keyword = roomObject.keyword,
            timestamp = roomObject.timestamp,
        )

    override fun toRoomObject(dataModel: SearchHistoryDataModel) =
        SearchHistoryRoomObject(
            id = dataModel.id,
            keyword = dataModel.keyword,
            timestamp = dataModel.timestamp
        )
}
