package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.Observable
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException

class MovieDetailsVMImpl(private val getMovieByIdUseCase: suspend (String) -> MovieDetailDto?) : MovieDetailsVM {

    override val movieState = Observable<MovieDetailDto?>(null)
    override val isLoading = Observable(false)
    override val error = Observable<String?>(null)

    private var loadJob: Job? = null

    override fun loadMovie(id: String) {

        loadJob?.cancel()


        loadJob = CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true
            error.value = null
            try {
                val movie = withContext(Dispatchers.IO) {
                    getMovieByIdUseCase(id)
                }
                movieState.value = movie
            } catch (e: Exception) {
                if (e !is CancellationException) {
                    error.value = e.message
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}