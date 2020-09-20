/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.DataModel
import kr.ohyung.domain.Entity

interface EntityMapper<D: DataModel, E: Entity> {
    fun toEntity(dataModel: D): E
    fun toDataModel(entity: E): D
    fun toEntities(dataModels: List<D>): List<E> = dataModels.map { toEntity(it) }
    fun toDataModels(entities: List<E>): List<D> = entities.map { toDataModel(it) }
}
