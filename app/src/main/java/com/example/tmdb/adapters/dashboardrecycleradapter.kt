package com.example.tmdb.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.MovieListData
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.MoviecardBinding


class Dashboardrecycleradapter(private val listener: OnClick) : RecyclerView.Adapter<Dashboardrecycleradapter.viewHolder>() {

    lateinit var binding : MoviecardBinding
    var moviesList: MovieListData? = null

    fun setMovieList(moviesList: MovieListData){
        this.moviesList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviecard, parent, false)

        binding = MoviecardBinding.bind(view)
        val viewHolderObject= viewHolder(view, binding)
        view.setOnClickListener {
            listener.onItemClicked(moviesList!!.results[viewHolderObject.absoluteAdapterPosition])
        }
        return viewHolderObject
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val BASE_URL = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList?.results?.get(position)
        if (dataObj != null) {
            holder.moviename.text = dataObj.title
        }
        holder.rating.rating= dataObj?.vote_average?.toFloat()?.div(2) ?: 0f

        Glide.with(holder.itemView.context).load(BASE_URL + dataObj?.poster_path).into(holder.binding.movieimg)


    }

    override fun getItemCount(): Int {
        if (moviesList == null)
        {
            Log.d("adapter", "null h")
            return 0
        }
        return moviesList!!.results.size
    }

     class viewHolder(view: View, val binding: MoviecardBinding): RecyclerView.ViewHolder(view) {
        var moviename = binding.movienameText

        //var movieimg = binding.movieimg
        var rating = binding.ratingBar

    }


}


interface OnClick{
    fun onItemClicked(item: MovieEntity)
}