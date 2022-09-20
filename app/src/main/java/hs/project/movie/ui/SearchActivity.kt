package hs.project.movie.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hs.project.movie.adapter.NaverPostAdapter
import hs.project.movie.data.ResponseNaverSearchPostItem
import hs.project.movie.databinding.ActivitySearchBinding
import hs.project.movie.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    private val naverPostAdapter by lazy {
        NaverPostAdapter()
    }

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initRecyclerView()

        viewModel.posts.observe(this, Observer {
            if (it.isNotEmpty()) {
                naverPostAdapter.setList(it)
                binding.tvNone.isVisible = false
                binding.rvPost.isVisible = true
            } else {
                binding.tvNone.isVisible = true
                binding.rvPost.isVisible = false
                Toast.makeText(this, "검색결과가 없습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        binding.btnSearch.setOnClickListener(this)
    }

    private fun initRecyclerView(){
        naverPostAdapter.setOnItemClickListener(object : NaverPostAdapter.OnPostClickListener {
            override fun onItemClick(data: ResponseNaverSearchPostItem) {
                Toast.makeText(this@SearchActivity, data.title, Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvPost.apply {
            adapter = naverPostAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnSearch.id -> {
                if (binding.etSearch.text.isNotEmpty()) {
                    viewModel.getPosts(binding.etSearch.text.toString())
                } else {
                    Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}