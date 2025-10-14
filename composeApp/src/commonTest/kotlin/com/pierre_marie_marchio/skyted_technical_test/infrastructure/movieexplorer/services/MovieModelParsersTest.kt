package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services

import kotlin.test.Test
import kotlin.test.assertEquals

class MovieParserTest {
    @Test
    fun `parseMovieModel should parse all fields`() {
        val json = """
            {
              "imdbID":"tt123",
              "Title":"Inception",
              "Poster":"url.jpg",
              "Plot":"A dream story",
              "Director":"Nolan",
              "imdbRating":"8.8"
            }
        """.trimIndent()

        val movie = parseMovieModel(json)

        assertEquals("tt123", movie.id)
        assertEquals("Inception", movie.title)
        assertEquals("url.jpg", movie.image)
        assertEquals("A dream story", movie.description)
        assertEquals("Nolan", movie.director)
        assertEquals("8.8", movie.rating)
    }
}

class MovieModelsParserTest {

    @Test
    fun `parseMovieModels should return list of movies`() {
        val json = """
            {
              "Search":[
                {"imdbID":"1","Title":"A"},
                {"imdbID":"2","Title":"B"}
              ]
            }
        """.trimIndent()

        val movies = parseMovieModels(json)

        assertEquals(2, movies.size)
        assertEquals("1", movies[0].id)
        assertEquals("A", movies[0].title)
        assertEquals("2", movies[1].id)
        assertEquals("B", movies[1].title)
    }
}