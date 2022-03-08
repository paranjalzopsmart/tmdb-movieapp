package com.example.tmdb.DI

import android.content.Context
import androidx.room.Room
import com.example.tmdb.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideFavouritesDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "favourites"
        )
            .build()

    @Singleton
    @Provides
    fun provideFavouritesDao(favData: MovieDatabase) =
        favData.movieDao()
}