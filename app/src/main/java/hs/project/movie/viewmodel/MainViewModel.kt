package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hs.project.movie.data.PopularMovieItem
import hs.project.movie.repo.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val movieRepository by lazy {
        MovieRepository()
    }

    private val _popularMovies = MutableLiveData<List<PopularMovieItem>>()
    val popularMovies : LiveData<List<PopularMovieItem>>
        get() = _popularMovies

    fun getPopularMovies(page: Int) = viewModelScope.launch {

        val response = movieRepository.getPopularMovies(page)

        Log.d(this@MainViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _popularMovies.postValue(movieRepository.getPopularMovies(page).body()?.results)
        } else {
            Log.e(this@MainViewModel.javaClass.name, response.message())
        }
    }

}