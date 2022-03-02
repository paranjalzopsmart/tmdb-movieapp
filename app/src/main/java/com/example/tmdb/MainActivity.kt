package com.example.tmdb

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.fragments.DashboardFragment
import com.example.tmdb.repository.Repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.dashboardViewModel


class MainActivity : AppCompatActivity() {

    lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)



        repository = Repository(
            movieApiInterface = movieApiInterface.getInstance(),
            databaseHelperImpl = DatabaseHelperImpl(MovieDatabase.DatabaseBuilder.getInstance(applicationContext).movieDao())
        )
        val dashboardViewModel = ViewModelProvider(this, MyViewModelFactory(repository))[dashboardViewModel::class.java]
        val dashboardFragment = DashboardFragment(viewModel = dashboardViewModel)


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, dashboardFragment)
            commit()
        }
    }

}