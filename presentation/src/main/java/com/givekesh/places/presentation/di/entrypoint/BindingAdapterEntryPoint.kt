package com.givekesh.places.presentation.di.entrypoint

import androidx.databinding.DataBindingComponent
import com.givekesh.places.presentation.di.component.BindingAdapterComponent
import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import com.givekesh.places.presentation.databinding.BindingAdapter
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingAdapterScope
@InstallIn(BindingAdapterComponent::class)
interface BindingAdapterEntryPoint : DataBindingComponent {
    override fun getBindingAdapter(): BindingAdapter
}