package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.EmptyState
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.ErrorState
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.LoadingState
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.NoResultsState
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components.MovieListContent
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components.MovieListSearchBar

@Composable
fun MovieListScreen(
    viewModel: MovieListVM,
    onMovieClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var movies by remember { mutableStateOf<List<MovieListItemDto>?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(viewModel) {
        viewModel.movieListState.observe { movies = it }
        viewModel.isLoading.observe { isLoading = it }
        viewModel.error.observe { error = it }
        viewModel.searchQuery.observe { searchQuery = it }
    }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            viewModel.loadMovieList(searchQuery)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        MovieListSearchBar(
            viewModel = viewModel,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                isLoading -> LoadingState()
                error != null -> ErrorState(error!!)
                movies != null && movies!!.isNotEmpty() -> MovieListContent(
                    movies = movies!!,
                    onMovieClick = onMovieClick
                )

                movies != null && movies!!.isEmpty() -> NoResultsState()

                else -> EmptyState()
            }
        }
    }
}