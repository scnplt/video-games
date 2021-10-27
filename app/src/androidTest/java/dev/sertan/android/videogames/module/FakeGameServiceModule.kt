package dev.sertan.android.videogames.module

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.sertan.android.videogames.data.service.FakeGameService
import dev.sertan.android.videogames.data.service.GameService
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [GameServiceModule::class])
internal object FakeGameServiceModule {

    @Provides
    @Singleton
    fun provideGameService(): GameService = FakeGameService()

}
