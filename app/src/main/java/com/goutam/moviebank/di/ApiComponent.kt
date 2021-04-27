package com.goutam.moviebank.di

import com.goutam.moviebank.repo.MoviesRepositoryImpl
import com.goutam.moviebank.ui.movielist.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(movieRepository: MoviesRepositoryImpl)

    fun injectContext(context: MoviesRepositoryImpl)

    fun injectToViewModel(movieListViewModel: MovieListViewModel)

}
