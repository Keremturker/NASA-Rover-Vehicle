package in_.turker.nasarovervehicle.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Kerem TÜRKER on 15.03.2022.
 */

fun ImageView.loadImagesWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}