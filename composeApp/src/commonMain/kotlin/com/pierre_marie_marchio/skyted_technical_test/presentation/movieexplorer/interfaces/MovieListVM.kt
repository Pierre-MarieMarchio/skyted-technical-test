package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.Observable

interface MovieListVM {
    val movieListState: Observable<List<MovieListItemDto>?>
    val isLoading: Observable<Boolean>
    val error: Observable<String?>
    val searchQuery: Observable<String>

    fun loadMovieList(query: String)
    fun updateSearchQuery(query: String)
}