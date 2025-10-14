package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListVMImplTest : BaseVMTest() {

    @Test
    fun `loadMovieList sets movieListState when use case returns data`() = runTest {
        val movies = listOf(
            MovieListItemDto("1", "A", "img1"),
            MovieListItemDto("2", "B", "img2")
        )
        val useCase: suspend (String) -> List<MovieListItemDto>? = { movies }

        val vm = MovieListVMImpl(
            searchMovieUseCase = useCase,
            mainDispatcher = testDispatcher,
            ioDispatcher = testDispatcher
        )

        vm.loadMovieList("query")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, vm.isLoading.value)
        assertEquals(movies, vm.movieListState.value)
        assertNull(vm.error.value)
    }

    @Test
    fun `loadMovieList sets error when use case throws exception`() = runTest {
        val useCase: suspend (String) -> List<MovieListItemDto>? = { throw RuntimeException("Fail") }

        val vm = MovieListVMImpl(
            searchMovieUseCase = useCase,
            mainDispatcher = testDispatcher,
            ioDispatcher = testDispatcher
        )

        vm.loadMovieList("query")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, vm.isLoading.value)
        assertNull(vm.movieListState.value)
        assertEquals("Fail", vm.error.value)
    }

    @Test
    fun `updateSearchQuery updates searchQuery observable`() = runTest {
        val useCase: suspend (String) -> List<MovieListItemDto>? = { emptyList() }

        val vm = MovieListVMImpl(
            searchMovieUseCase = useCase,
            mainDispatcher = testDispatcher,
            ioDispatcher = testDispatcher
        )

        vm.updateSearchQuery("new query")

        assertEquals("new query", vm.searchQuery.value)
    }
}