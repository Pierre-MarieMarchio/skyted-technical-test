package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.service

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie

fun Movie.toMovieDetailDto() = MovieDetailDto(
    id = this.id,
    title = this.title,
    image = this.image,
    description = this.description,
    director = this.director,
    rating = this.rating
)

fun Movie.toMovieListItemDto() = MovieListItemDto(
    id = this.id,
    title = this.title,
    image = this.image

)