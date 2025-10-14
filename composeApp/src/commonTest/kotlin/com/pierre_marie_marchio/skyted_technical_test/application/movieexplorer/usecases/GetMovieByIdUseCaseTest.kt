package com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.usecases

import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class FakeMovieRepositoryForGet : MovieRepository {
    var movieToReturn: Movie? = null
    var lastId: String? = null

    override suspend fun getMovieById(id: String): Movie? {
        lastId = id
        return movieToReturn
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return emptyList()
    }
}

class GetMovieByIdUseCaseTest {

    private val fakeRepository = FakeMovieRepositoryForGet()
    private val useCase = GetMovieByIdUseCase(fakeRepository)

    @Test
    fun `execute returns MovieDetailDto when movie exists`() = runTest {
        fakeRepository.movieToReturn = Movie("1", "A", "img1", "desc", "director", "5")

        val result = useCase.execute("1")

        assertEquals("1", fakeRepository.lastId)
        assertEquals("1", result?.id)
        assertEquals("A", result?.title)
        assertEquals("img1", result?.image)
        assertEquals("desc", result?.description)
        assertEquals("director", result?.director)
        assertEquals("5", result?.rating)
    }

    @Test
    fun `execute returns null when movie does not exist`() = runTest {
        fakeRepository.movieToReturn = null

        val result = useCase.execute("1")

        assertEquals("1", fakeRepository.lastId)
        assertNull(result)
    }
}