package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
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
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "⬅️",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = movie?.title ?: "Movie Details",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> LoadingState()
                error != null -> ErrorState(error!!)
                movie != null -> MovieDetailsContent(movie!!)
                else -> EmptyState()
            }
        }
    }
}