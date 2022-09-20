package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hs.project.movie.data.ResponseNaverSearchPostItem
import hs.project.movie.repo.NaverPostRepository
import hs.project.movie.repo.PostRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val naverPostRepository by lazy {
        NaverPostRepository()
    }

    private val _posts = MutableLiveData<List<ResponseNaverSearchPostItem>>()
    val posts : LiveData<List<ResponseNaverSearchPostItem>>
        get() = _posts

    fun getPosts(search:String) = viewModelScope.launch {

        val response = naverPostRepository.getPosts(search)

        Log.d(this@SearchViewModel.javaClass.name, response.body().toString())

        if (response.isSuccessful) {
            _posts.postValue(naverPostRepository.getPosts(query = search).body()?.items)
        } else {
            Log.e(this@SearchViewModel.javaClass.name, response.message())
        }

    }

}