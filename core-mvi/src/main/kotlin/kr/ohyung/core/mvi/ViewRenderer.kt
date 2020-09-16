package kr.ohyung.core.mvi

interface ViewRenderer<STATE: ViewState> {
    fun render(state: STATE)
}
