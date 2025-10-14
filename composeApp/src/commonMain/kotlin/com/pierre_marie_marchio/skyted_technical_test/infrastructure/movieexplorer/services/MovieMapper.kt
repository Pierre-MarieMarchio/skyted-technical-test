package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services

import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.models.MovieModel


fun MovieModel.toEntity() = Movie(
    id = this.id,
    title = title,
    description = description,
    image = image,
    director = director,
    rating = rating
)

fun Movie.toDbModel() = MovieModel(
    id = this.id,
    title = title,
    description = description,
    image = image,
    director = director,
    rating = rating
)