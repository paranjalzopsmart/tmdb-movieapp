package com.example.tmdb

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.fragments.DashboardFragment
import com.example.tmdb.repository.Repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.DashboardViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var repository: Repository
    @Inject lateinit var MovieApiInterfaceObj: MovieApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val tmdbAppObj= TmdbApplication()

        tmdbAppObj.tmdbComponent.inject(this)


        repository = Repository(
            MovieApiInterfaceObj,
            databaseHelperImpl = DatabaseHelperImpl(MovieDatabase.DatabaseBuilder.getInstance(applicationContext).movieDao())
        )
        val DashboardViewModel = ViewModelProvider(this, MyViewModelFactory(repository))[DashboardViewModel::class.java]
        val dashboardFragment = DashboardFragment(viewModel = DashboardViewModel)


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, dashboardFragment)
            commit()
        }
    }

}