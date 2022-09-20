package hs.project.movie.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.movie.adapter.PostAdapter
import hs.project.movie.data.DailyBoxOffice
import hs.project.movie.databinding.ActivityMainBinding
import hs.project.movie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val postAdapter by lazy {
        PostAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initRecyclerView()
        viewModel.getPosts("20220909")

        viewModel.posts.observe(this, Observer {
            postAdapter.setList(it)
        })
    }

    private fun init() {
        binding.btnMove.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnMove.id -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
        }
    }
}