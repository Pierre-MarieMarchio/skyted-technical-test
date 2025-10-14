package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto

data class MovieDetailDto(
    val id: String,
    val title: String,
    val image: String?,
    val description: String?,
    val director: String?,
    val rating: String?
)
