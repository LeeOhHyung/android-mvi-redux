/*
 * Created by Lee Oh Hyung on 2020/09/20.
 */
package kr.ohyung.data.exception

sealed class NetworkException(message: String) : Exception(message) {
    data class BadRequestException(override val message: String) : NetworkException(message) // 400
    data class UnauthorizedException(override val message: String) : NetworkException(message) // 401
    data class ForbiddenException(override val message: String) : NetworkException(message) // 403
    data class NotFoundException(override val message: String) : NetworkException(message) // 404
    data class RequestTimeoutException(override val message: String) : NetworkException(message) // 408
    data class TooManyRequestsException(override val message: String) : NetworkException(message) // 429
    data class InternalServerException(override val message: String) : NetworkException(message) // 500
    data class UnknownException(override val message: String) : NetworkException(message) // ETC
}
