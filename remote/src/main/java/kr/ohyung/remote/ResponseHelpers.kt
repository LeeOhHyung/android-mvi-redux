/*
 * Created by Lee Oh Hyung on 2020/10/09.
 */
package kr.ohyung.remote

import com.squareup.moshi.Moshi
import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import kr.ohyung.data.exception.NetworkException
import kr.ohyung.remote.response.UnsplashErrorResponseJsonAdapter
import retrofit2.HttpException
import java.lang.Exception

internal class ResponseSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): Single<T> =
        upstream.onErrorResumeNext { throwable ->
            if(throwable is HttpException) {
                val errorMessage: String = throwable.errorMessage
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


val HttpException.errorMessage: String
    get() = try {
        val bufferedSource = response()?.errorBody()?.source()
        val moshi: Moshi = Moshi.Builder().build()
        val errorResponse = UnsplashErrorResponseJsonAdapter(moshi)
            .fromJson(bufferedSource)
        errorResponse?.errors?.joinToString() ?: ""
    } catch (e: NullPointerException) {
        this.message.toString()
    } catch (e: Exception) {
        this.message.toString()
    }
