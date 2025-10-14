package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.application.movieexplorer.dto.MovieDetailDto

@Composable
fun MovieDetailsContent(movie: MovieDetailDto) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MovieHeaderCard(title = movie.title, imageUrl = movie.image)
        MovieMetadataCard(director = movie.director, rating = movie.rating)
        MovieDescriptionCard(description = movie.description)
    }
}