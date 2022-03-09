package com.example.tmdb.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.tmdb.views.adapters.DashboardRecyclerAdapter
import com.example.tmdb.views.adapters.OnClick
import com.example.tmdb.views.viewmodels.DashboardViewModel

class DashboardFragment(private val viewModel: DashboardViewModel) : Fragment(), OnClick {

    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    private lateinit var binding: DashboardBinding
    private val adapter = DashboardRecyclerAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardBinding.inflate(layoutInflater)
        binding.movieslist.layoutManager = GridLayoutManager(activity, 3)

        binding.chipFavourites.setOnClickListener {
            viewModel.showFav()
        }

        binding.chipToprated.setOnClickListener {
            viewModel.changeCategory("top_rated")
        }

        binding.chipPopular.setOnClickListener {
            viewModel.changeCategory("popular")
        }

        binding.chipUpcoming.setOnClickListener {
            viewModel.changeCategory("upcoming")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieslist.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager = GridLayoutManager(activity, 3)
            addOnScrollListener(this@DashboardFragment.scrollListener)
        }
        binding.movieslist.adapter = adapter
        binding.movieslist.layoutManager = GridLayoutManager(activity, 3)
        viewModel.movieList.observe(
            viewLifecycleOwner
        ) {
            adapter.setMovieList(it)
        }
        viewModel.favouriteMovies.observe(viewLifecycleOwner) {

            adapter.setMovieList(it)
        }
    }
    override fun onItemClicked(item: MovieEntity) {
        val movieDescription = MovieDescription(item, viewModel)
        viewModel.changeMovie(item.id.toString())
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescription)
            commit()
            addToBackStack("DashboardFragment")
        }
    }
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val notLoadingOrLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = notLoadingOrLastPage && isAtLastItem && isNotAtBeginning &&
                isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getNextPage()
                isScrolling = false
            }
        }
    }
}
