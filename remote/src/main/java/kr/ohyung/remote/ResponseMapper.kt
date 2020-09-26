/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.remote

import kr.ohyung.data.model.DataModel

interface ResponseMapper<R: Response, D: DataModel> {
    fun toDataModel(response: R): D
    fun toDataModels(responses: List<R>): List<D> = responses.map { toDataModel(it) }
}
