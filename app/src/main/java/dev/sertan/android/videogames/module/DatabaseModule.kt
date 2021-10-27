package dev.sertan.android.videogames.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sertan.android.videogames.data.database.GameDAO
import dev.sertan.android.videogames.data.database.GameDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideGameDAO(@ApplicationContext context: Context): GameDAO {
        val database = Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            GameDatabase::class.java.name
        ).build()
        return database.gameDAO()
    }

}
