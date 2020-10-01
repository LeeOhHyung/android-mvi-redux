/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.source.local

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import kr.ohyung.data.exception.DatabaseException
import kr.ohyung.data.model.DataModel
import kr.ohyung.domain.exception.DuplicatedEntityException
import kr.ohyung.domain.exception.EntityNotFoundException

interface LocalDataSource<D: DataModel> {
    fun insert(dataModel: D): Completable
    fun insert(dataModels: List<D>): Completable
    fun update(dataModel: D): Completable
    fun delete(dataModel: D): Completable
    fun getAll(): Single<List<D>>
    fun getCount(): Single<Int>
    fun drop(): Completable
}

internal class RoomSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>) =
        upstream.onErrorResumeNext { throwable ->
            when(throwable) {
                is DatabaseException.NotFoundException -> Single.error(EntityNotFoundException(throwable.message))
                is DatabaseException.DuplicatedException -> Single.error(DuplicatedEntityException(throwable.message))
                else -> Single.error(throwable)
            }
        }
}

internal class RoomCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable) =
        upstream.onErrorResumeNext { throwable ->
            when(throwable) {
                is DatabaseException.NotFoundException -> Completable.error(EntityNotFoundException(throwable.message))
                is DatabaseException.DuplicatedException -> Completable.error(DuplicatedEntityException(throwable.message))
                else -> Completable.error(throwable)
            }
        }
}

internal fun <T> Single<T>.compose() = compose(RoomSingleTransformer())
internal fun Completable.compose() = compose(RoomCompletableTransformer())
