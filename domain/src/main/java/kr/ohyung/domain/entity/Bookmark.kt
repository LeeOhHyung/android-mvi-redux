/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class Bookmark(
    val id: String,
    val description: String,
    val thumbnail: String,
    val likes: Int,
    val username: String
) : Entity
