package dev.sertan.android.videogames.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.sertan.android.videogames.data.database.GameDAO
import dev.sertan.android.videogames.data.database.GameDatabase
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
internal class FakeDatabaseModule {

    @Provides
    @Singleton
    fun provideGameDAO(@ApplicationContext context: Context): GameDAO {
        val database = Room.inMemoryDatabaseBuilder(context, GameDatabase::class.java).build()
        return database.gameDAO()
    }

}
