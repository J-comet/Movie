package hs.project.movie.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.movie.adapter.PostAdapter
import hs.project.movie.databinding.ActivityMain22Binding
import hs.project.movie.viewmodel.Main22ViewModel

class Main22Activity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivityMain22Binding.inflate(layoutInflater)
    }

    private val viewModel: Main22ViewModel by viewModels()

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
            postAdapter.submitList(it)
        })
    }

    private fun init() {
        binding.btnMove.setOnClickListener(this)
    }

    private fun initRecyclerView(){
        binding.rvPost.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@Main22Activity)
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