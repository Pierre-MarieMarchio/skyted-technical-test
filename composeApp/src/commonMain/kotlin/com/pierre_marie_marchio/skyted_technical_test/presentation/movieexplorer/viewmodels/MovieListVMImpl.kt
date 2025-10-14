package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.viewmodels

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.Observable
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException

class MovieListVMImpl(private val searchMovieUseCase: suspend (String) -> List<MovieListItemDto>?) : MovieListVM {
    override val movieListState = Observable<List<MovieListItemDto>?>(null)
    override val isLoading = Observable(false)
    override val error = Observable<String?>(null)
    override val searchQuery = Observable("")


    private var loadJob: Job? = null

    override fun loadMovieList(query: String) {
        loadJob?.cancel()

        loadJob = CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true
            error.value = null
            try {
                val movies = withContext(Dispatchers.IO) {
                    searchMovieUseCase(query)
                }
                movieListState.value = movies
            } catch (e: Exception) {
                if (e !is CancellationException) {
                    error.value = e.message
                }
            } finally {
                isLoading.value = false
            }
        }
    }

    override fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }

}