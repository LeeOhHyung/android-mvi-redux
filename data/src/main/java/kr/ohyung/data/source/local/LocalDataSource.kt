/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Completable
import io.reactivex.Single
import kr.ohyung.data.model.DataModel

interface LocalDataSource<D: DataModel> {
    fun insert(dataModel: D): Completable
    fun insert(dataModels: List<D>): Completable
    fun update(dataModel: D): Completable
    fun delete(dataModel: D): Completable
    fun getAll(): Single<List<D>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}
