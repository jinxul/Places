package com.givekesh.places.presentation

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.givekesh.places.presentation.di.component.BindingAdapterComponentBuilder
import com.givekesh.places.presentation.di.entrypoint.BindingAdapterEntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var bindingAdapterProvider: Provider<BindingAdapterComponentBuilder>

    override fun onCreate() {
        super.onCreate()
        injectBindingAdapter()
    }

    private fun injectBindingAdapter() {
        val component = bindingAdapterProvider.get().build()
        val entryPoint = EntryPoints.get(component, BindingAdapterEntryPoint::class.java)
        DataBindingUtil.setDefaultComponent(entryPoint)
    }
}