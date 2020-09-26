/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local

import kr.ohyung.data.model.DataModel

interface RoomObjectMapper<R: RoomObject, D: DataModel> {
    fun toDataModel(roomObject: R): D
    fun toDataModels(roomObjects: List<R>): List<D> = roomObjects.map { toDataModel(it) }
    fun toRoomObject(dataModel: D): R
    fun toRoomObjects(dataModels: List<D>): List<R> = dataModels.map { toRoomObject(it) }
}
