package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hs.project.movie.data.DailyBoxOffice
import hs.project.movie.repo.NaverPostRepository
import hs.project.movie.repo.PostRepository
import kotlinx.coroutines.launch

class Main22ViewModel : ViewModel() {

    private val postRepository by lazy {
        PostRepository()
    }

    private val naverPostRepository by lazy {
        NaverPostRepository()
    }

    private val _posts = MutableLiveData<List<DailyBoxOffice>>()
    val posts : LiveData<List<DailyBoxOffice>>
        get() = _posts

    fun getPosts(targetDt:String) = viewModelScope.launch {

        val response = postRepository.getPosts(targetDt)

        Log.d(this@Main22ViewModel.javaClass.name, response.toString())

        if (response.isSuccessful) {
            _posts.postValue(postRepository.getPosts(targetDt = targetDt).body()?.boxOfficeResult?.dailyBoxOfficeList)
        } else {
            Log.e(this@Main22ViewModel.javaClass.name, response.message())
        }
    }

}