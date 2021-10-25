package dev.sertan.android.videogames.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import dev.sertan.android.videogames.R

@GlideModule
internal class VideoGamesGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val cacheSize = 1024 * 1024 * 15L
        val diskCacheSize = 1024 * 1024 * 100L
        val defaultRequestOption = RequestOptions()
            .error(R.drawable.ic_error).placeholder(R.drawable.ic_refresh)
            .centerCrop()

        builder.setMemoryCache(LruResourceCache(cacheSize))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSize))
        builder.setDefaultRequestOptions(defaultRequestOption)
    }

}
