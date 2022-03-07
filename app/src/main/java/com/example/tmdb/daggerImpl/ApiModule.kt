package com.example.tmdb.daggerImpl

import com.example.tmdb.apiServices.MovieApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): MovieApiInterface = MovieApiInterface.getInstance()
}