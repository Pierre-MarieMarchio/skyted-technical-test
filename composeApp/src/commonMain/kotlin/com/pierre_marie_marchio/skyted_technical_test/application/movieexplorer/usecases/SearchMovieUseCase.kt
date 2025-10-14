package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases

import com.pierre_marie_marchio.skyted_technical_test.application.common.bases.UseCaseBase
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.service.toMovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) : UseCaseBase<String, List<MovieListItemDto>?>() {
    override suspend fun execute(params: String): List<MovieListItemDto>? {
        val movies = repository.searchMovies(params)
        return movies.map { it.toMovieListItemDto() }
    }
}