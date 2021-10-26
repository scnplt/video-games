package dev.sertan.android.videogames.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sertan.android.videogames.data.service.analytics.AnalyticsService
import dev.sertan.android.videogames.data.service.analytics.FirebaseAnalyticsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsService(): AnalyticsService = FirebaseAnalyticsService()

}
