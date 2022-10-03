package hs.project.movie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import hs.project.movie.adapter.PopularMovieAdapter
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularMovies.collect {
                    popularAdapter.submitList(it)
                }
            }
        }

    }

    private fun initRecyclerView(){
        binding.rvMoive.apply {
            adapter = popularAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }
}