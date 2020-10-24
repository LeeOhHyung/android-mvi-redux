/*
 * Created by Lee Oh Hyung on 2020/10/17.
 */
package kr.ohyung.mvi.home.mvi

import kr.ohyung.core.mvi.ViewAction
import kr.ohyung.domain.entity.Bookmark

sealed class HomeViewAction : ViewAction {
    object Nothing : HomeViewAction()
    object GetLocationAndPhotos : HomeViewAction()
    data class PhotoLoadMore(val query: String, val page: Int) : HomeViewAction()
    data class AddBookmark(val bookmark: Bookmark) : HomeViewAction()
    data class DeleteBookmark(val id: String) : HomeViewAction()
}
