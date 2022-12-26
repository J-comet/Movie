package hs.project.movie.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hs.project.movie.data.model.DetailMovie
import hs.project.movie.data.repository.MovieRepository
import hs.project.movie.utils.StateFlowUtil.getMutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    companion object {
        const val DETAIL_MOVIE = "detail_movie"
    }

    private val movieId: Int = stateHandle[DetailMovieActivity.MOVIE_ID] ?: -1

    init {
        stateHandle.keys().forEach { key ->
            Timber.d("Received [$key]=[${stateHandle.get<Any>(key)}]")
        }
        getDetailPopularMovie()
    }

     private val _detailPopularMovie = stateHandle.getMutableStateFlow(
        DETAIL_MOVIE,
        DetailMovie()
    )

    val detailPopularMovie: StateFlow<DetailMovie>
        get() = _detailPopularMovie.asStateFlow()

    private fun getDetailPopularMovie() = viewModelScope.launch {

        if (movieId == -1) return@launch
        val response = repository.getDetailPopularMovie(movieId)

        Timber.d(this@DetailMovieViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _detailPopularMovie.value = repository.getDetailPopularMovie(movieId).body()!!
        } else {
            Timber.e(this@DetailMovieViewModel.javaClass.name, response.message())
        }
    }

}