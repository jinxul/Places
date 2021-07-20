package com.givekesh.places.presentation.databinding

import com.bumptech.glide.RequestManager
import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import javax.inject.Inject

@BindingAdapterScope
class BindingAdapter @Inject constructor(
    private val glide: RequestManager
) {

}