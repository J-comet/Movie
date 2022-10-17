package hs.project.movie.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hs.project.movie.adapter.LoadStateAdapter
import hs.project.movie.adapter.PopularMovieAdapter
import hs.project.movie.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
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
                viewModel.popularMovies.collectLatest {
                    popularAdapter.submitData(it)
                }
            }
        }
    }

    private fun initRecyclerView() {

        val footerAdapter = LoadStateAdapter { popularAdapter.retry() }
        val gridLayoutManager = GridLayoutManager(this, 3)

        binding.rvMoive.apply {
            adapter =
                popularAdapter.withLoadStateFooter(footer = footerAdapter)
            layoutManager = gridLayoutManager
            gridLayoutManager.spanSizeLookup =  object : GridLayoutManager.SpanSizeLookup() {  // Gridlayouamanger 때 Center 맞추기위함
                override fun getSpanSize(position: Int): Int {
                    return if ((position == popularAdapter.itemCount) && footerAdapter.itemCount > 0) {
                        3
                    } else {
                        1
                    }
                }
            }
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }

//    private fun popularMoviesHandle(state: State) {
//        when (state) {
//            is State.Loading -> {
//                binding.loading.isVisible = true
//            }
//            is State.Success -> {
//                popularAdapter.submitList(state.data as List<PopularMovieItem>)
//                binding.loading.isVisible = false
//            }
//            is State.Error -> {
//                Toast.makeText(this, "데이터를 불러오는데 실패했습니다", Toast.LENGTH_SHORT).show()
//                binding.loading.isVisible = false
//            }
//        }
//    }
}