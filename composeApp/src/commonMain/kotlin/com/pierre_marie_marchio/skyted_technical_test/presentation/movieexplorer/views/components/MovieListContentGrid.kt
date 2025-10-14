package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieListItemDto

@Composable
fun MovieListContentGrid(
    movies: List<MovieListItemDto>,
    onMovieClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            MovieGridItem(
                movie = movie,
                onClick = { onMovieClick(movie.id) }
            )
        }
    }
}