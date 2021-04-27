package com.goutam.moviebank.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.goutam.moviebank.application.MovieBankApplication
import com.goutam.moviebank.repo.MoviesRepositoryImpl
import com.goutam.moviebank.webservice.MovieService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApiModule {

    @Singleton
    @Provides
    fun getMovieServiceInstance(): MovieService{
        val client = OkHttpClient.Builder()
            .connectTimeout(120 , TimeUnit.SECONDS)
            .readTimeout(120 , TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context{
        return MovieBankApplication.instance
    }

    @Singleton
    @Provides
    fun provideMovieRepo(): MoviesRepositoryImpl{
        return MoviesRepositoryImpl()
    }
}
