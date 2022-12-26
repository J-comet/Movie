package hs.project.movie.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
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
import hs.project.movie.ui.base.BindActivity
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DetailMovieActivity : BindActivity<ActivityDetailMovieBinding>(R.layout.activity_detail_movie) {

    companion object {
        const val MOVIE_ID = "movie_id"
    }

    private val viewModel by viewModels<DetailMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailPopularMovie.collect { detailData ->
                    setData(detailData)
                }
            }
        }
    }

    override fun init() {
        binding.ivBack.setOnClickListener { backPressed() }
    }


    private fun setData(detailData: DetailMovie) {
        detailData.also {

            Timber.d("detail = $it")

            if (!it.posterPath.isNullOrEmpty()) {
                binding.ivThumb.load(Config.IMG_BASE_URL + it.posterPath) {
//                    crossfade(true)
                    placeholder(R.color.color_img_load)
                    error(R.color.color_img_load)
                    transformations(RoundedCornersTransformation(10f))
                }
            } else {
                binding.ivThumb.load(R.color.color_img_load) {
//                    crossfade(true)
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