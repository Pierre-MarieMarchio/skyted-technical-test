package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases

import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FakeMovieRepositoryForSearch : MovieRepository {
    var moviesToReturn: List<Movie> = emptyList()
    var lastQuery: String? = null

    override suspend fun getMovieById(id: String): Movie? = null

    override suspend fun searchMovies(query: String): List<Movie> {
        lastQuery = query
        return moviesToReturn
    }
}

class SearchMovieUseCaseTest {

    private val fakeRepository = FakeMovieRepositoryForSearch()
    private val useCase = SearchMovieUseCase(fakeRepository)

    @Test
    fun `execute returns list of MovieListItemDto`() = runTest {
        fakeRepository.moviesToReturn = listOf(
            Movie("1", "A", "img1", null, null, null),
            Movie("2", "B", "img2", null, null, null)
        )

        val result = useCase.execute("query")

        assertEquals("query", fakeRepository.lastQuery)
        assertEquals(2, result!!.size)

        assertEquals("1", result[0].id)
        assertEquals("A", result[0].title)
        assertEquals("img1", result[0].image)

        assertEquals("2", result[1].id)
        assertEquals("B", result[1].title)
        assertEquals("img2", result[1].image)
    }

    @Test
    fun `execute returns empty list when no movies found`() = runTest {
        fakeRepository.moviesToReturn = emptyList()

        val result = useCase.execute("query")

        assertEquals("query", fakeRepository.lastQuery)
        assertEquals(0, result!!.size)
    }
}