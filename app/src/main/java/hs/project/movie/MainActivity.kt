package hs.project.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import hs.project.movie.api.PostAPI
import hs.project.movie.data.ResponseDailyMovies
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.repo.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            binding.tvResult.text = getPosts("20220901").toString()
        }
    }

    private suspend fun getPosts(targetDt: String): ResponseDailyMovies? {
        return PostRepository.instance.getPosts(targetDt)
    }
}