/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.model

data class BookmarkDataModel(
    val id: String,
    val description: String,
    val thumbnail: String,
    val likes: Int,
    val username: String
) : DataModel
