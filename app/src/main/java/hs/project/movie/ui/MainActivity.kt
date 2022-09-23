package hs.project.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import hs.project.movie.R
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getPopularMovies(1)

        viewModel.popularMovies.observe(this) {
            binding.tvResult.text = it.toString()
        }

    }
}