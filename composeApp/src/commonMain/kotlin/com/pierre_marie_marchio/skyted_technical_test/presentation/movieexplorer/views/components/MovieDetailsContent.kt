package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
    ) {
        MovieHeaderCard(
            title = movie.title,
            imageUrl = movie.image
        )

        MovieDescription(description = movie.description)

        Spacer(modifier = Modifier.height(16.dp))

        MovieMetadata(
            director = movie.director,
            rating = movie.rating
        )
    }
}