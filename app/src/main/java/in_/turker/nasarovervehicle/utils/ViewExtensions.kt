package in_.turker.nasarovervehicle.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import in_.turker.nasarovervehicle.R

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */

fun ImageView.loadImagesWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.image_place_holder).error(R.drawable.image_error)
        .into(this)
}

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}