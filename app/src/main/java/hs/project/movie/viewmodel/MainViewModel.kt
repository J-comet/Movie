package hs.project.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hs.project.movie.Config
import hs.project.movie.api.PostAPI
import hs.project.movie.api.RetrofitClient
import hs.project.movie.data.DailyBoxOffice
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val postAPI = RetrofitClient.getInstance().create(PostAPI::class.java)

    private val _posts = MutableLiveData<List<DailyBoxOffice>>()
    val posts : LiveData<List<DailyBoxOffice>>
        get() = _posts

    fun getPosts(targetDt:String) = viewModelScope.launch {
        Log.d(this@MainViewModel.javaClass.name, postAPI.getPosts(secretKey = Config.SECRET_KEY, targetDt = targetDt).toString())
        _posts.value = postAPI.getPosts(secretKey = Config.SECRET_KEY, targetDt = targetDt).boxOfficeResult.dailyBoxOfficeList
    }
}