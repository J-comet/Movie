package hs.project.movie.ui.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hs.project.movie.data.model.PopularMovieItem
import hs.project.movie.data.repository.MovieRepository
import hs.project.movie.utils.StateFlowUtil.getMutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    companion object {
        private const val POPULAR_MOVIES = "popularMovies"
    }

    private val _popularMovies = stateHandle.getMutableStateFlow(
        POPULAR_MOVIES,
        emptyList<PopularMovieItem>()
    )

    val popularMovies: StateFlow<List<PopularMovieItem>>
        get() = _popularMovies
            .asStateFlow()
            .onSubscription {
                if (popularMovies.value.isEmpty()) {
                    popularMovies()
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )


    private fun popularMovies() = viewModelScope.launch {
        val response = repository.getPopularMovies(page = 1)

        if (response.isSuccessful) {
            _popularMovies.value = response.body()?.results ?: emptyList()
        } else {
            _popularMovies.value = emptyList()
            Log.e(this@MainViewModel.javaClass.name, "error = ${response.errorBody()}")
        }
    }

}