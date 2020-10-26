/*
 * Created by Lee Oh Hyung on 2020/10/24.
 */
package kr.ohyung.mvi.utility.widget

interface Binder<in I : HolderItem> {
    fun bindTo(item: I)
}
