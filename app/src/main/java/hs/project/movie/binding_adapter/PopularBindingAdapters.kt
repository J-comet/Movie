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
            placeholder(R.color.color_img_load)
            error(R.color.color_img_load)
            transformations(RoundedCornersTransformation(10f))
        }
    } else {
        view.load(R.color.color_img_load) {
            crossfade(true)
            transformations(RoundedCornersTransformation(10f))
        }
    }
}