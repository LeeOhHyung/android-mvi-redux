/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.mapper

import kr.ohyung.data.model.DataModel
import kr.ohyung.local.RoomObject

interface RoomObjectMapper<R: RoomObject, D: DataModel> {
    fun toDataModel(roomObject: R): D
    fun toDataModels(roomObjects: List<R>): List<D> = roomObjects.map { toDataModel(it) }
}
