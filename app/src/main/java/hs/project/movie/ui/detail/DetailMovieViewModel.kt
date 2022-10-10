package hs.project.movie.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hs.project.movie.data.model.DetailMovie
import hs.project.movie.data.repository.MovieRepository
import hs.project.movie.utils.StateFlowUtil.getMutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    companion object {
        const val DETAIL_MOVIE = "detail_movie"
    }

    init {
        stateHandle.keys().forEach { key ->
            Log.d("DetailMovieViewModel", "Received [$key]=[${stateHandle.get<Any>(key)}]")
        }
    }

     private val _detailPopularMovie = stateHandle.getMutableStateFlow(
        DETAIL_MOVIE,
        DetailMovie()
    )

    val detailPopularMovie: StateFlow<DetailMovie>
        get() = _detailPopularMovie.asStateFlow()

    fun getDetailPopularMovie(movieId: Int) = viewModelScope.launch {

        val response = repository.getDetailPopularMovie(movieId)

        Log.d(this@DetailMovieViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _detailPopularMovie.value = repository.getDetailPopularMovie(movieId).body()!!
        } else {
            Log.e(this@DetailMovieViewModel.javaClass.name, response.message())
        }
    }

}