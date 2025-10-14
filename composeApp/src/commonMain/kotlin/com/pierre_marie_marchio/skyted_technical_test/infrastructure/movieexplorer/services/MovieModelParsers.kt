package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services

import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.models.MovieModel

fun parseMovieModel(json: String): MovieModel {
    fun extract(key: String): String? {
        return json.substringAfter("\"$key\":\"", "")
            .substringBefore("\"", "")
            .ifEmpty { null }
    }

    return MovieModel(
        id = extract("imdbID") ?: "",
        title = extract("Title") ?: "",
        image = extract("Poster") ?: "",
        description = extract("Plot"),
        director = extract("Director"),
        rating = extract("imdbRating")
    )
}

fun parseMovieModels(json: String): List<MovieModel> {
    val resultsJson = json.substringAfter("\"Search\":[", "")
        .substringBefore("]", "")
    val regex = "\\{[^}]*\\}".toRegex()
    return regex.findAll(resultsJson).map { matchResult ->
        parseMovieModel(matchResult.value)
    }.toList()
}