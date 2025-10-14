package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.EmptyState
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.ErrorState
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.views.components.LoadingState
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components.MovieDetailsContent

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsVM,
    movieId: String,
    onBackClick: () -> Unit
) {
    var movie by remember { mutableStateOf<MovieDetailDto?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(viewModel) {
        viewModel.movieState.observe { movie = it }
        viewModel.isLoading.observe { isLoading = it }
        viewModel.error.observe { error = it }
    }

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("⬅\uFE0F", style = MaterialTheme.typography.titleMedium)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            when {
                isLoading -> LoadingState()
                error != null -> ErrorState(error = error!!)
                movie != null -> MovieDetailsContent(movie = movie!!)
                else -> EmptyState()
            }
        }
    }
}