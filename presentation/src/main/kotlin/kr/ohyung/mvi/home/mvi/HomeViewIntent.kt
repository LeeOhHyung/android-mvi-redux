/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import kr.ohyung.core.mvi.ViewIntent
import kr.ohyung.domain.entity.Bookmark

sealed class HomeViewIntent : ViewIntent {
    object Noting : HomeViewIntent()
    object Retry : HomeViewIntent()
    object InitHomeScreen : HomeViewIntent()
    data class PhotoLoadMore(val query: String, val page: Int) : HomeViewIntent()
    data class AddBookmark(val bookmark: Bookmark) : HomeViewIntent()
    data class DeleteBookmark(val id: String) : HomeViewIntent()
}
