package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hs.project.movie.data.DetailPopularMovie
import hs.project.movie.data.PopularMovieItem
import hs.project.movie.repo.MovieRepository
import kotlinx.coroutines.launch

class DetailPopularViewModel : ViewModel() {

    private val movieRepository by lazy {
        MovieRepository()
    }

    private val _detailPopularMovie = MutableLiveData<DetailPopularMovie>()
    val detailPopularMovie : LiveData<DetailPopularMovie>
        get() = _detailPopularMovie

    fun getDetailPopularMovie(movieId: Int) = viewModelScope.launch {

        val response = movieRepository.getDetailPopularMovie(movieId)

        Log.d(this@DetailPopularViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _detailPopularMovie.postValue(movieRepository.getDetailPopularMovie(movieId).body())
        } else {
            Log.e(this@DetailPopularViewModel.javaClass.name, response.message())
        }
    }

}