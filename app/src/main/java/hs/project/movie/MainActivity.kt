package hs.project.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.movie.adapter.PostAdapter
import hs.project.movie.api.PostAPI
import hs.project.movie.data.DailyBoxOffice
import hs.project.movie.data.ResponseDailyMovies
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.repo.PostRepository
import hs.project.movie.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val postAdapter by lazy {
        PostAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        viewModel.getPosts("20220909")

        viewModel.posts.observe(this, Observer {
            postAdapter.setList(it)
        })
    }

    private fun initRecyclerView(){
        postAdapter.setOnItemClickListener(object : PostAdapter.OnPostClickListener {
            override fun onItemClick(data: DailyBoxOffice) {
                Toast.makeText(this@MainActivity, data.movieNm, Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvPost.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}