/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.exception

class EntityNotFoundException(message: String = "Entity Not Found in Database") : Exception(message)
