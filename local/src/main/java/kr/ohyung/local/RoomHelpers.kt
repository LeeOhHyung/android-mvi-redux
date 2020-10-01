/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local

import androidx.room.EmptyResultSetException
import io.reactivex.*
import kr.ohyung.data.exception.DatabaseException

internal class RoomSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>) =
        upstream.onErrorResumeNext {
            if(it is EmptyResultSetException) {
                Single.error(DatabaseException.NotFoundException(it.message.toString()))
            } else {
                Single.error(it)
            }
        }
}

internal class RoomCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable) =
        upstream.onErrorResumeNext {
            if(it is EmptyResultSetException)
                Completable.error(DatabaseException.NotFoundException(it.message.toString()))
            else
                Completable.error(it)
        }
}

internal fun <T> Single<T>.compose() = compose(RoomSingleTransformer())
internal fun Completable.compose() = compose(RoomCompletableTransformer())
