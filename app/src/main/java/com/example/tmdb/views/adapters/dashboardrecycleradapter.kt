package com.example.tmdb.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.MoviecardBinding
import com.example.tmdb.models.MovieEntity

class DashboardRecyclerAdapter(private val listener: OnClick) : RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder>() {

    private lateinit var binding: MoviecardBinding
    private var moviesList: List<MovieEntity> = emptyList()

    fun setMovieList(moviesList: List<MovieEntity>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviecard, parent, false)

        binding = MoviecardBinding.bind(view)
        val viewHolderObject = ViewHolder(view, binding)
        view.setOnClickListener {
            listener.onItemClicked(moviesList[viewHolderObject.absoluteAdapterPosition])
        }
        return viewHolderObject
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baseURL = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList.getOrNull(position)
        if (dataObj != null) {
            holder.moviename.text = dataObj.title
        }
        holder.rating.rating = dataObj?.vote_average?.toFloat()?.div(2) ?: 0f

        Glide.with(holder.itemView.context).load(baseURL + dataObj?.poster_path).into(holder.binding.movieimg)
    }

    override fun getItemCount(): Int {
        if (moviesList.isEmpty()) {
            Log.d("adapter", "null h")
            return 0
        }
        return moviesList.size
    }

    class ViewHolder(view: View, val binding: MoviecardBinding) : RecyclerView.ViewHolder(view) {
        var moviename = binding.movienameText

        var rating = binding.ratingBar
    }
}

interface OnClick {
    fun onItemClicked(item: MovieEntity)
}
