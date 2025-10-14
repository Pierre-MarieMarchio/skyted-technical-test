package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto

@Composable
fun MovieListContent(
    movies: List<MovieListItemDto>,
    onMovieClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            MovieListItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }
    }
}