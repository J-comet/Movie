package hs.project.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.movie.R
import hs.project.movie.adapter.PopularMovieAdapter
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()
    private val popularAdapter by lazy {
        PopularMovieAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()

        viewModel.getPopularMovies(1)

        viewModel.popularMovies.observe(this) {
            popularAdapter.submitList(it)
        }
    }

    private fun initRecyclerView(){
        binding.rvMoive.apply {
            adapter = popularAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }
}