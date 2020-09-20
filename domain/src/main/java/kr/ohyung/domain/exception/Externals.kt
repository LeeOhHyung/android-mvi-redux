/*
 * Created by Lee Oh Hyung on 2020/09/20.
 */
package kr.ohyung.domain.exception

sealed class Externals(message: String) : Exception(message) {
    data class BadRequestException(override val message: String) : Externals(message) // 400
    data class UnauthorizedException(override val message: String) : Externals(message) // 401
    data class ForbiddenException(override val message: String) : Externals(message) // 403
    data class NotFoundException(override val message: String) : Externals(message) // 404
    data class RequestTimeoutException(override val message: String) : Externals(message) // 408
    data class TooManyRequestsException(override val message: String) : Externals(message) // 429
    data class InternalServerException(override val message: String) : Externals(message) // 500
}
