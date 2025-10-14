package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.models

data class MovieModel(
    val id: String,
    val title: String,
    val image: String,
    val description: String?,
    val director: String?,
    val rating: String?,
)
