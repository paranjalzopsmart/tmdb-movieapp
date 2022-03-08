package com.example.tmdb

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.database.DatabaseHelperImpl
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.fragments.DashboardFragment
import com.example.tmdb.repository.Repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val dashboardFragment = DashboardFragment(viewModel = dashboardViewModel)


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, dashboardFragment)
            commit()
        }
    }

}