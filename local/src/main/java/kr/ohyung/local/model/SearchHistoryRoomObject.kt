/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.ohyung.local.RoomObject

@Entity(tableName = "search_history")
data class SearchHistoryRoomObject(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val keyword: String,
    val timestamp: Long = System.currentTimeMillis()
) : RoomObject
