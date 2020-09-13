package kr.ohyung.core.mvi

interface ViewRenderer<STATE: UiState> {
    fun render(state: STATE)
}
