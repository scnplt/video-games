package dev.sertan.android.videogames.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dev.sertan.android.videogames.data.service.analytics.AnalyticsService
import dev.sertan.android.videogames.data.service.analytics.FirebaseAnalyticsService

@Module
@InstallIn(ActivityComponent::class)
internal object AnalyticsModule {

    @Provides
    @ActivityScoped
    fun provideAnalyticsService(): AnalyticsService = FirebaseAnalyticsService()

}
