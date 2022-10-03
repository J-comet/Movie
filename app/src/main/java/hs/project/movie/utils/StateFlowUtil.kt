package hs.project.movie.utils

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow


object StateFlowUtil {

    class SaveableMutableStateFlow<T>(
        private val saveStateHandle: SavedStateHandle,
        private val key: String,
        initialValue: T
    ) {
        private val state: StateFlow<T> = saveStateHandle.getStateFlow(key, initialValue)
        var value: T
            get() = state.value
            set(value) {
                saveStateHandle[key] = value
            }

        fun asStateFlow(): StateFlow<T> = state
    }

    fun <T> SavedStateHandle.getMutableStateFlow(
        key: String,
        initialValue: T
    ): SaveableMutableStateFlow<T> =
        SaveableMutableStateFlow(this, key, initialValue)

}

