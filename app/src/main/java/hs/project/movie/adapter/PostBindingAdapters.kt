package hs.project.movie.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import hs.project.movie.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        view.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_arrow_drop_down_circle_24)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}