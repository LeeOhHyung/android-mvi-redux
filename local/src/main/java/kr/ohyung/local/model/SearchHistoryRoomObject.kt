/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kr.ohyung.local.RoomObject

@Entity(tableName = "search_history", indices = [Index(value = ["keyword"], unique = true)])
data class SearchHistoryRoomObject(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val keyword: String,
    val timestamp: Long = System.currentTimeMillis()
) : RoomObject
