package hs.project.movie.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import hs.project.movie.Config
import hs.project.movie.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {
        view.load(Config.IMG_BASE_URL + imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_arrow_drop_down_circle_24)
            error(R.drawable.ic_baseline_sync_problem_24)
            transformations(RoundedCornersTransformation(10f))
        }
    } else {
        view.load(R.color.teal_200) {
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}