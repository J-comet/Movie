package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hs.project.movie.data.PopularMovieItem
import hs.project.movie.repo.MovieRepository
import hs.project.movie.utils.StateFlowUtil.getMutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    companion object {
        const val POPULAR_MOVIES = "popularMovies"
    }

    private val _popularMovies = stateHandle.getMutableStateFlow(
        POPULAR_MOVIES,
        emptyList<PopularMovieItem>()
    )
    val popularMovies: StateFlow<List<PopularMovieItem>>
        get() = _popularMovies.asStateFlow()

    init {
        if (popularMovies.value.isEmpty()) {
            popularMovies()
        }
    }

    fun popularMovies() = viewModelScope.launch {
        val response = repository.getPopularMovies(page = 1)

        if (response.isSuccessful) {
            _popularMovies.value = response.body()?.results ?: emptyList()
        } else {
            Log.e(this@MainViewModel.javaClass.name, "error = ${response.errorBody()}")
        }
    }

    /*private val _popularMovies = MutableLiveData<List<PopularMovieItem>>()
    val popularMovies: LiveData<List<PopularMovieItem>>
        get() = _popularMovies

    fun getPopularMovies(page: Int) = viewModelScope.launch {

        val response = repository.getPopularMovies(page)

        Log.d(this@MainViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _popularMovies.postValue(repository.getPopularMovies(page).body()?.results)
        } else {
            Log.e(this@MainViewModel.javaClass.name, response.message())
        }
    }*/

}