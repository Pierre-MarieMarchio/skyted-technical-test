package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases

import com.pierre_marie_marchio.skyted_technical_test.application.common.bases.UseCaseBase
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.service.toMovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository

class GetMovieByIdUseCase(private val repository: MovieRepository) : UseCaseBase<String, MovieDetailDto?>() {

    override suspend fun execute(params: String): MovieDetailDto? {
        val movie = repository.getMovieById(params)
        return movie?.toMovieDetailDto()
    }

}