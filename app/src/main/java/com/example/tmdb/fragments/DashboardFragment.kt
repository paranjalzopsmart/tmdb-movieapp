package com.example.tmdb.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.adapters.OnClick
import com.example.tmdb.adapters.dashboardrecycleradapter
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.data.MovieListData
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.viewmodels.dashboardViewModel

class DashboardFragment(private val viewModel: dashboardViewModel) : Fragment(), OnClick {
    
    lateinit var binding: DashboardBinding


    private val retrofitService = movieApiInterface.getInstance()
    val adapter = dashboardrecycleradapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipFavourites.setOnClickListener(View.OnClickListener {
            viewModel.showFav()
        })

        binding.chipToprated.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("top_rated")
        })


        binding.chipPopular.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("popular")
        })


        binding.chipUpcoming.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("upcoming")


        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieslist.setHasFixedSize(true)
        binding.movieslist.setItemViewCacheSize(20)


        viewModel.getMovieListquery("popular")

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            binding.movieslist.layoutManager = GridLayoutManager(activity, 3)
            adapter.setMovieList(it)
            binding.movieslist.adapter = adapter
        })

        viewModel.favouriteMovies.observe(viewLifecycleOwner){
            val data = MovieListData(1, it, 19, 1)
            binding.movieslist.layoutManager = GridLayoutManager(activity, 2)
            adapter.setMovieList(data)
            binding.movieslist.adapter = adapter
        }

    }

    override fun onItemClicked(item: MovieEntity) {
        //Log.d("check", "Item clicked")

        val movieDescription=MovieDescription(item, viewModel)
        viewModel.changeMovie(item.id.toString())
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescription)
            commit()
            addToBackStack("DashboardFragment")
        }
    }
}