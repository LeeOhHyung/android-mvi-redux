/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.mapper

import kr.ohyung.data.model.DataModel
import kr.ohyung.domain.Entity

interface EntityMapper<M: DataModel, E: Entity> {
    fun toEntity(dataModel: M): E
    fun toDataModel(entity: E): M
    fun toEntities(dataModels: List<M>): List<E> = dataModels.map { toEntity(it) }
    fun toDataModels(entities: List<E>): List<M> = entities.map { toDataModel(it) }
}
