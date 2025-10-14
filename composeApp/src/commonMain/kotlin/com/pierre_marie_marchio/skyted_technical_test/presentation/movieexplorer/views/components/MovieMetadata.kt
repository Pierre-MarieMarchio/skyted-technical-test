package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MovieMetadata(
    director: String?,
    rating: String?
) {
    Column {
        director?.let {
            Text(
                text = "Director: $it",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }

        rating?.let {
            Text(
                text = "Rating: $it",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}