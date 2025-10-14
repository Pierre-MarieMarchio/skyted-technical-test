package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces

import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.Observable

interface MovieDetailsVM {
    val movieState: Observable<MovieDetailDto?>
    val isLoading: Observable<Boolean>
    val error: Observable<String?>

    fun loadMovie(id: String)

}