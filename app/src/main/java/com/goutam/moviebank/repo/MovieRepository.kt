package com.goutam.moviebank.repo

import com.goutam.moviebank.model.MODMovieResponse
import com.goutam.moviebank.retrofit.RetrofitResult

interface MovieRepository {

    suspend fun getMovies(): RetrofitResult<MODMovieResponse>

}
