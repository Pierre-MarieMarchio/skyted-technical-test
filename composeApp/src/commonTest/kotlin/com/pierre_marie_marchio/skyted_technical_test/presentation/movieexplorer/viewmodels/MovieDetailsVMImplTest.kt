package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsVMImplTest : BaseVMTest() {

    @Test
    fun `loadMovie sets movieState when use case returns data`() = runTest {
        val movieDto = MovieDetailDto("1", "Title", "img", "desc", "dir", "5")
        val useCase: suspend (String) -> MovieDetailDto? = { movieDto }

        val vm = MovieDetailsVMImpl(
            useCase,
            mainDispatcher = testDispatcher,
            ioDispatcher = testDispatcher
        )

        vm.loadMovie("1")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(true, !vm.isLoading.value)
        assertEquals(movieDto, vm.movieState.value)
        assertNull(vm.error.value)
    }

    @Test
    fun `loadMovie sets error when use case throws exception`() = runTest {
        val useCase: suspend (String) -> MovieDetailDto? = { throw RuntimeException("Oops") }

        val vm = MovieDetailsVMImpl(
            useCase,
            mainDispatcher = testDispatcher,
            ioDispatcher = testDispatcher
        )

        vm.loadMovie("1")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, vm.isLoading.value)
        assertNull(vm.movieState.value)
        assertEquals("Oops", vm.error.value)
    }
}