package hs.project.movie.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import hs.project.movie.Config
import hs.project.movie.R
import hs.project.movie.data.DetailPopularMovie
import hs.project.movie.databinding.ActivityDetailPopularBinding
import hs.project.movie.viewmodel.DetailPopularViewModel

@AndroidEntryPoint
class DetailPopularActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailPopularBinding.inflate(layoutInflater)
    }

    private var id = -1

    private val viewModel by viewModels<DetailPopularViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        id = intent.getIntExtra("id", -1)
        Log.d("========================= ${this.javaClass.name} =========================", "id = $id")

        if (id == -1) return

//        viewModel.getDetailPopularMovie(id)

        viewModel.detailPopularMovie.observe(this) { detailData ->
            setData(detailData)
        }
    }

    private fun setData(detailData: DetailPopularMovie){
        detailData.also {

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

        }
    }
}