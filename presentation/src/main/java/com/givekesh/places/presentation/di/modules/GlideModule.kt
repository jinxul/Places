package com.givekesh.places.presentation.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.givekesh.places.R
import com.givekesh.places.presentation.di.component.BindingAdapterComponent
import com.givekesh.places.presentation.di.scope.BindingAdapterScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(BindingAdapterComponent::class)
object GlideModule {
    @BindingAdapterScope
    @Provides
    fun provideDiskCacheStrategy(): DiskCacheStrategy = DiskCacheStrategy.AUTOMATIC

    @BindingAdapterScope
    @Provides
    fun provideRequestOptionsFactory(
        strategy: DiskCacheStrategy
    ): Glide.RequestOptionsFactory = Glide.RequestOptionsFactory {
        RequestOptions().diskCacheStrategy(strategy)
            .placeholder(R.drawable.ic_baseline_image)
            .error(R.drawable.ic_baseline_broken_image)
    }

    @BindingAdapterScope
    @Provides
    fun provideRequestOptions(
        factory: Glide.RequestOptionsFactory
    ): RequestOptions = factory.build()

    @BindingAdapterScope
    @Provides
    fun provideGlide(
        @ApplicationContext context: Context,
        requestOptions: RequestOptions
    ): RequestManager = Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
    
}