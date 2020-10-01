/*
 * Created by Lee Oh Hyung on 2020/10/01.
 */
package kr.ohyung.domain.exception

class DuplicatedEntityException(override val message: String) : IllegalArgumentException(message)
