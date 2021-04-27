package com.goutam.moviebank.ui.movielist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.goutam.moviebank.R
import com.goutam.moviebank.databinding.ItemMovieListBinding
import com.goutam.moviebank.model.MovieResult

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    var movieList: MutableList<MovieResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemMovieListBinding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(itemMovieListBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun bindMovieList(listOfMovie: List<MovieResult>){
        Log.e("Response", Gson().toJson(listOfMovie))
        movieList.clear()
        movieList.addAll(listOfMovie)
        notifyDataSetChanged()
    }

    inner class MovieHolder(private val itemMovieListBinding: ItemMovieListBinding): RecyclerView.ViewHolder(itemMovieListBinding.root) {
        fun bind(details: MovieResult) {
            itemMovieListBinding.apply {
                txtMovieName.text = details.title
                val imgPath = "https://image.tmdb.org/t/p/w500/" + details.poster_path
                Glide.with(itemMovieListBinding.root)
                    .load(imgPath)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgPoster)
            }
        }
    }
}
