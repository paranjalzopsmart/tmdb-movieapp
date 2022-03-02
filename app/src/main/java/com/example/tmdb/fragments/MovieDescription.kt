package com.example.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding
import com.example.tmdb.viewmodels.dashboardViewModel

class MovieDescription(val MovieDataObject: MovieEntity,
    val viewModel: dashboardViewModel
) : Fragment() {

    lateinit var binding: FragmentMovieDescriptionBinding
    val Base_URL: String = "https://image.tmdb.org/t/p/original"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDescriptionBinding.inflate(layoutInflater)
        binding.movieTitle.text = MovieDataObject.title
        binding.movieOverview.text = MovieDataObject.overview
        binding.movieReleaseDate.text = MovieDataObject.release_date

        context.let {
            if (it != null) {
                Glide.with(it).load(Base_URL + MovieDataObject.poster_path)
                    .into(binding.moviePoster)
            }
        }
        context.let {
            if (it != null) {
                Glide.with(it).load(Base_URL + MovieDataObject.backdrop_path)
                    .into(binding.movieBackdrop)
            }
        }

        viewModel.isFav.observe(viewLifecycleOwner) {
            if (viewModel.isFav.value!!){
                //Log.d("desc", "white")
                binding.button.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_favorite_red
                    )
                )
            }
            else{
                binding.button.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_favorite_white
                    )
                )
            }
        }

        binding.button.setOnClickListener {
            viewModel.onButtonPress(MovieDataObject)
        }

        return binding.root
    }
}
