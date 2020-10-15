/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.domain.entity

import kr.ohyung.domain.Entity

data class PhotoSummary(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String,
    val thumbnail: String,
    val fullSizeImageUrl: String,
    val regularSizeImageUrl: String,
    val likes: Int,
    val username: String
) : Entity
