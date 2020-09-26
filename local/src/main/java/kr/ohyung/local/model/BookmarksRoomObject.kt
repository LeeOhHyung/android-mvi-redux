/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.ohyung.local.RoomObject

@Entity(tableName = "bookmarks")
data class BookmarksRoomObject(
    @PrimaryKey
    val id: String, // same as photosResponse id field
    val description: String,
    val thumbnail: String,
    val likes: String,
    val username: String
) : RoomObject
