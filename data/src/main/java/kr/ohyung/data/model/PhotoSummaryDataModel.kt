/*
 * Created by Lee Oh Hyung on 2020/09/19.
 */
package kr.ohyung.data.model

data class PhotoSummaryDataModel(
    val id: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String,
    val thumbnail: String,
    val regularImageUrl: String,
    val likes: Int,
    val username: String
) : DataModel
