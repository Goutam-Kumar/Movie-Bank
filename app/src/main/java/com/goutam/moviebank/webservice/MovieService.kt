package com.goutam.moviebank.webservice

import com.goutam.moviebank.model.MODMovieResponse
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET("{version}/movie/popular")
    suspend fun getAllPopularMovies(
            @Path("version") version: String,
            @Query("api_key")  apiKey: String, @Header("Content-Type") type: String): Response<MODMovieResponse>


}
