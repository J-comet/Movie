package hs.project.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hs.project.movie.data.model.DetailPopularMovie


class DetailPopularViewModel : ViewModel() {

    private val _detailPopularMovie = MutableLiveData<DetailPopularMovie>()
    val detailPopularMovie : LiveData<DetailPopularMovie>
        get() = _detailPopularMovie
//
//    fun getDetailPopularMovie(movieId: Int) = viewModelScope.launch {
//
//        val response = movieRepository.getDetailPopularMovie(movieId)
//
//        Log.d(this@DetailPopularViewModel.javaClass.name, response.toString())
//
//        if (response.isSuccessful) {
//            _detailPopularMovie.value = movieRepository.getDetailPopularMovie(movieId).body()
//        } else {
//            Log.e(this@DetailPopularViewModel.javaClass.name, response.message())
//        }
//    }

}