/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.DomainEntity

data class PhotoSummary(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String,
    val thumbnail: String,
    val likes: Int,
    val username: String
) : DomainEntity
