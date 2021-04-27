package com.goutam.moviebank.repo

import android.content.Context
import com.goutam.moviebank.R
import com.goutam.moviebank.di.DaggerApiComponent
import com.goutam.moviebank.model.MODMovieResponse
import com.goutam.moviebank.retrofit.RetrofitCallProcessor
import com.goutam.moviebank.retrofit.RetrofitResult
import com.goutam.moviebank.webservice.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.net.URLDecoder
import javax.inject.Inject

class MoviesRepositoryImpl: MovieRepository {
    @Inject
    lateinit var movieService: MovieService
    @Inject
    lateinit var context: Context

    init {
        DaggerApiComponent.create().inject(this)
        DaggerApiComponent.create().injectContext(this)
    }

    override suspend fun getMovies(): RetrofitResult<MODMovieResponse> {
        return withContext(Dispatchers.IO){
            RetrofitCallProcessor.processCall {
                movieService.getAllPopularMovies(3.toString(), context.getString(R.string.movie_api_key), "application/json")
            }
        }
    }
}
