package com.example.tmdb.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.MovieData
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.databinding.ActivityMainBinding.inflate
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding


class MovieDescription(val MovieDataObject: MovieData) : Fragment() {

    lateinit var binding: FragmentMovieDescriptionBinding
    val Base_URL: String = "https://image.tmdb.org/t/p/original"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

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
        return binding.root
    }
}