/*
 * Created by Lee Oh Hyung on 2020/10/09.
 */
package kr.ohyung.remote

import io.reactivex.*
import kr.ohyung.data.exception.NetworkException
import retrofit2.HttpException

internal class ResponseSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): Single<T> =
        upstream.onErrorResumeNext { throwable ->
            if(throwable is HttpException) {
                val errorMessage = throwable.response()?.errorBody()?.string().toString()
                when(throwable.code()) {
                    400 -> Single.error(NetworkException.BadRequestException(errorMessage))
                    401 -> Single.error(NetworkException.UnauthorizedException(errorMessage))
                    404 -> Single.error(NetworkException.NotFoundException(errorMessage))
                    else -> Single.error(NetworkException.UnknownException(errorMessage))
                }
            } else {
                Single.error(throwable)
            }
        }
}

internal class ResponseCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable): Completable =
        upstream.onErrorResumeNext { throwable ->
            if(throwable is HttpException) {
                val errorMessage = throwable.message.toString()
                when(throwable.code()) {
                    400 -> Completable.error(NetworkException.BadRequestException(errorMessage))
                    401 -> Completable.error(NetworkException.UnauthorizedException(errorMessage))
                    404 -> Completable.error(NetworkException.NotFoundException(errorMessage))
                    else -> Completable.error(NetworkException.UnknownException(errorMessage))
                }
            } else {
                Completable.error(throwable)
            }
        }
}

internal fun <T> Single<T>.compose() = compose(ResponseSingleTransformer())
internal fun Completable.compose() = compose(ResponseCompletableTransformer())
