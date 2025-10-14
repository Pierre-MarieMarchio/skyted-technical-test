package com.pierre_marie_marchio.skyted_technical_test

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases.GetMovieByIdUseCase
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases.SearchMovieUseCase
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.remote.MovieExplorerApiClient
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.repositories.MovieRepositoryImpl
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels.MovieDetailsVMImpl
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels.MovieListVMImpl

object DependencyContainer {
    private const val API_KEY = AppEnvironment.API_KEY

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl<Nothing>(
            client = MovieExplorerApiClient(apiKey = API_KEY)
        )
    }

    val movieDetailsVM: MovieDetailsVM by lazy {
        val getMovieByIdUseCase = GetMovieByIdUseCase(movieRepository)
        MovieDetailsVMImpl(
            getMovieByIdUseCase = { id -> getMovieByIdUseCase.execute(id) }
        )
    }

    val movieListVM: MovieListVM by lazy {
        val searchMovieUseCase = SearchMovieUseCase(movieRepository)
        MovieListVMImpl(
            searchMovieUseCase = { query -> searchMovieUseCase.execute(query) }
        )
    }
}
