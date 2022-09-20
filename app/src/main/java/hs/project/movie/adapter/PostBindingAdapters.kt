package hs.project.movie.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import hs.project.movie.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        view.load(imageUrl) {
            crossfade(true)
            error(R.drawable.ic_baseline_error_24)
            placeholder(R.drawable.ic_baseline_arrow_drop_down_circle_24)
            transformations(CircleCropTransformation())
        }
    }
}