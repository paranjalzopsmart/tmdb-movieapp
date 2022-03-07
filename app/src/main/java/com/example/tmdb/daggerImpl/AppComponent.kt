package com.example.tmdb.daggerImpl

import com.example.tmdb.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent{

    fun inject(target: MainActivity)
}