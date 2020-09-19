/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.model

import kr.ohyung.domain.entity.PhotoSummary as Entity

data class PhotoSummaryEntity(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String,
    val thumbnail: String,
    val likes: Int,
    val username: String
) : DataModel

fun PhotoSummaryEntity.toEntity() =
    Entity(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        thumbnail = thumbnail,
        likes = likes,
        username = username
    )

fun List<PhotoSummaryEntity>.toEntity() = map { it.toEntity() }
