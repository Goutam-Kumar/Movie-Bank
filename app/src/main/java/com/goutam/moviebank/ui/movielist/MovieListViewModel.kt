package com.goutam.moviebank.ui.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goutam.moviebank.di.DaggerApiComponent
import com.goutam.moviebank.model.MovieResult
import com.goutam.moviebank.repo.MoviesRepositoryImpl
import com.goutam.moviebank.retrofit.RetrofitResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel: ViewModel() {
    private val _movieList = MutableLiveData<List<MovieResult>>()
    val movieList: LiveData<List<MovieResult>> = _movieList
    private val _serviceException = MutableLiveData<String>()
    val serviceException: LiveData<String> = _serviceException

    @Inject
    lateinit var movieRepository: MoviesRepositoryImpl

    init {
        DaggerApiComponent.create().injectToViewModel(this)
    }

    fun getAllPopularMovies() {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        when (val response = movieRepository.getMovies()) {
            is RetrofitResult.Success -> {
                _movieList.value = response.data.results
            }
            is RetrofitResult.Error -> {
                Log.e("Response", response.exception)
                _serviceException.value = response.exception
            }
        }
    }
}
