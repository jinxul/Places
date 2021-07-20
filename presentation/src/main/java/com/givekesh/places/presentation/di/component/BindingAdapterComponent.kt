package com.givekesh.places.presentation.di.component

import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingAdapterScope
@DefineComponent(parent = SingletonComponent::class)
interface BindingAdapterComponent

@DefineComponent.Builder
interface BindingAdapterComponentBuilder {
    fun build(): BindingAdapterComponent
}