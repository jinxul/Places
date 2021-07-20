package com.givekesh.places.presentation.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import javax.inject.Inject

@BindingAdapterScope
class BindingAdapter @Inject constructor(
    private val glide: RequestManager
) {
    @BindingAdapter("loadImage")
    fun loadWithGlide(view: ImageView, url: String) {
        glide.load(url).into(view)
    }

    @BindingAdapter("setUnicodeText")
    fun setText(view: TextView, text: String) {
        view.text = text.replace("\\u200C", "\u200C")
    }
}