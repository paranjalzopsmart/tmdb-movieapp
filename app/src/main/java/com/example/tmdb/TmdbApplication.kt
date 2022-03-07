package com.example.tmdb

import android.app.Application
import com.example.tmdb.daggerImpl.AppComponent
import com.example.tmdb.daggerImpl.AppModule
import com.example.tmdb.daggerImpl.DaggerAppComponent

class TmdbApplication : Application() {

    var tmdbComponent = initDagger(this)

    private fun initDagger(app: TmdbApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}