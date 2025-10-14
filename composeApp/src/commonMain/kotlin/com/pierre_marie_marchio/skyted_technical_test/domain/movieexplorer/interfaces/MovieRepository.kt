package com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces

import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie

interface MovieRepository {
    suspend fun getMovieById(id: String): Movie?
    suspend fun searchMovies(query: String): List<Movie>
}

