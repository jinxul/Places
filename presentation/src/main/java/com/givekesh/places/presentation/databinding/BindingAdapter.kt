package com.givekesh.places.presentation.databinding

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.givekesh.places.R
import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import javax.inject.Inject

@BindingAdapterScope
class BindingAdapter @Inject constructor(
    private val glide: RequestManager
) {
    @BindingAdapter("loadImage")
    fun loadWithGlide(view: ImageView, url: String) {
        glide.load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.scaleType = ImageView.ScaleType.FIT_XY
                    return false
                }
            })
            .into(view)
    }

    @BindingAdapter("setUnicodeText")
    fun setText(view: TextView, text: String) {
        view.text = text.replace("\\u200C", "\u200C")
    }

    @BindingAdapter("setRating")
    fun setRating(view: RatingBar, score: Double) {
        view.rating = score.toFloat()
        val colorRes = when {
            score > 4 -> R.color.ratingGood
            score > 3 -> R.color.ratingNormal
            score > 2 -> R.color.ratingFair
            score > 1 -> R.color.ratingPoor
            else -> R.color.ratingVeryPoor
        }
        val color = ContextCompat.getColor(view.context, colorRes)
        view.progressTintList = ColorStateList.valueOf(color)
    }
}