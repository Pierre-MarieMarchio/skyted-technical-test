package com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation

sealed class Screen {

    data class MovieList(val initialQuery: String = "") : Screen()
    data class MovieDetails(val movieId: String) : Screen()

}