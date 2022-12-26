package hs.project.movie.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import hs.project.movie.Config
import hs.project.movie.R
import hs.project.movie.data.model.DetailMovie
import hs.project.movie.databinding.ActivityDetailMovieBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    private var id = -1

    private val viewModel by viewModels<DetailMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getIntExtra("id", -1)
        Log.d("========================= ${this.javaClass.name} =========================", "id = $id")

        if (id == -1) return

        init()
        viewModel.getDetailPopularMovie(id)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailPopularMovie.collect { detailData ->
                    setData(detailData)
                }
            }
        }
    }

    private fun init(){
        binding.ivBack.setOnClickListener { backPressed() }
    }

    private fun setData(detailData: DetailMovie){
        detailData.also {

            Log.d("detail", "$it")

            if (!it.posterPath.isNullOrEmpty()) {
                binding.ivThumb.load(Config.IMG_BASE_URL + it.posterPath) {
                    crossfade(true)
                    placeholder(R.color.color_img_load)
                    error(R.color.color_img_load)
                    transformations(RoundedCornersTransformation(10f))
                }
            } else {
                binding.ivThumb.load(R.color.color_img_load) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(10f))
                }
            }
            binding.tvTitle.text = it.title
            binding.tvDate.text = it.releaseDate

            binding.tvGenres.text = "정보 없음"
            if (it.genres.isNotEmpty()) {
                val sb = StringBuilder()
                sb.append("| ")
                it.genres.forEach { genre ->
                    sb.append(genre.name)
                    sb.append(" | ")
                }
                binding.tvGenres.text = sb.toString()
            }

            binding.tvRuntime.text = "${it.runtime}분"
            binding.tvVoteAverage.text = "평점 ${it.voteAverage}"
            binding.tvOverview.text = it.overview
        }
    }

    private fun backPressed() {
        finish()
    }
}