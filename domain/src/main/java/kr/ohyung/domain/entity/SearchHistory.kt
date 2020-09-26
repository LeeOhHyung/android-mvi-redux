/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class SearchHistory(
    val id: Long,
    val keyword: String,
    val timestamp: Long
) : Entity
