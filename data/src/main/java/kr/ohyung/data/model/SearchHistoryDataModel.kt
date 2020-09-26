/*
 * Created by Lee Oh Hyung on 2020/09/26.
 */
package kr.ohyung.data.model

data class SearchHistoryDataModel(
    val id: Long,
    val keyword: String,
    val timestamp: Long
) : DataModel
