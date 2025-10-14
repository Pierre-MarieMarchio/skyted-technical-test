package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.repositories


import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class FakeApiClient(private val responses: Map<String, String>) : ApiClient {
    val calledUrls = mutableListOf<String>()

    override suspend fun get(url: String): String {
        calledUrls.add(url)
        return responses[url] ?: ""
    }
}


class MovieRepositoryImplTest {

    @Test
    fun `getMovieById should fetch from API and parse correctly`() = runTest {
        val movieJson = """
            {
              "imdbID":"tt123",
              "Title":"Inception",
              "Poster":"poster.jpg",
              "Plot":"A dream story",
              "Director":"Nolan",
              "imdbRating":"8.8"
            }
        """.trimIndent()

        val fakeClient = FakeApiClient(
            mapOf("https://www.omdbapi.com/?i=tt123" to movieJson)
        )

        val repo = MovieRepositoryImpl<ApiClient>(client = fakeClient, dao = null)

        val movie = repo.getMovieById("tt123")

        // Vérifications
        assertEquals(1, fakeClient.calledUrls.size)
        assertEquals("https://www.omdbapi.com/?i=tt123", fakeClient.calledUrls[0])
        assertEquals("tt123", movie.id)
        assertEquals("Inception", movie.title)
        assertEquals("poster.jpg", movie.image)
        assertEquals("A dream story", movie.description)
        assertEquals("Nolan", movie.director)
        assertEquals("8.8", movie.rating)
    }

    @Test
    fun `searchMovies should fetch list from API and parse correctly`() = runTest {
        val searchJson = """
            {
              "Search":[
                {"imdbID":"1","Title":"Movie A","Poster":"a.jpg","Plot":"Plot A","Director":"Dir A","imdbRating":"7.0"},
                {"imdbID":"2","Title":"Movie B","Poster":"b.jpg","Plot":"Plot B","Director":"Dir B","imdbRating":"8.0"}
              ]
            }
        """.trimIndent()

        val fakeClient = FakeApiClient(
            mapOf("https://www.omdbapi.com/?s=action" to searchJson)
        )

        val repo = MovieRepositoryImpl<ApiClient>(client = fakeClient, dao = null)

        val movies = repo.searchMovies("action")

        assertEquals(1, fakeClient.calledUrls.size)
        assertEquals("https://www.omdbapi.com/?s=action", fakeClient.calledUrls[0])
        assertEquals(2, movies.size)

        assertEquals("1", movies[0].id)
        assertEquals("Movie A", movies[0].title)
        assertEquals("a.jpg", movies[0].image)
        assertEquals("Plot A", movies[0].description)
        assertEquals("Dir A", movies[0].director)
        assertEquals("7.0", movies[0].rating)

        assertEquals("2", movies[1].id)
        assertEquals("Movie B", movies[1].title)
        assertEquals("b.jpg", movies[1].image)
        assertEquals("Plot B", movies[1].description)
        assertEquals("Dir B", movies[1].director)
        assertEquals("8.0", movies[1].rating)
    }

    @Test
    fun `should throw exception if no client or DAO`() = runTest {
        val repo = MovieRepositoryImpl<Any>(client = null, dao = null)

        try {
            repo.getMovieById("tt123")
            fail("Expected IllegalStateException")
        } catch (e: IllegalStateException) {
            assertEquals("Aucun client API ni DAO disponible pour cette requête", e.message)
        }

        try {
            repo.searchMovies("action")
            fail("Expected IllegalStateException")
        } catch (e: IllegalStateException) {
            assertEquals("Aucun client API ni DAO disponible pour cette requête", e.message)
        }
    }
}