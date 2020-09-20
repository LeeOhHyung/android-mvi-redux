/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote.mapper

import kr.ohyung.data.model.DataModel
import kr.ohyung.remote.Response

interface ResponseMapper<R: Response, D: DataModel> {
    fun toDataModel(response: R): D
    fun toDataModels(responses: List<R>): List<D> = responses.map { toDataModel(it) }
}
