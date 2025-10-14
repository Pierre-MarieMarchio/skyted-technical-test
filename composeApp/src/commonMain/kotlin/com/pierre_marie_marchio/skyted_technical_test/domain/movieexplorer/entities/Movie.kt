package com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities

data class Movie (
    val id: String,
    val title: String,
    val image: String,
    val description: String?,
    val director: String?,
    val rating: String?,
    )