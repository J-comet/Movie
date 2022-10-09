package hs.project.movie.utils

sealed class State {
    data class Success(val data: Any?) : State()
    object Loading : State()
    object Error : State()
}