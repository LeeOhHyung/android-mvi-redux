/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.exception

sealed class DatabaseException(message: String) : Exception(message) {
    data class EntityResultException(override val message: String) : DatabaseException(message) // EmptyResultSetException
}
