/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.exception

internal class NoParamsException(message: String = "params never be null for this use case.") : Exception(message)
