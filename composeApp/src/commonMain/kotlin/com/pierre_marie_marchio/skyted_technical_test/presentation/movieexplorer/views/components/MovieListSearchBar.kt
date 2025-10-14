package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListSearchBar(
    viewModel: MovieListVM,
    delayMillis: Long = 500L,
    modifier: Modifier = Modifier
) {
    var textValue by remember { mutableStateOf("") }

    LaunchedEffect(textValue) {
        delay(delayMillis)
        viewModel.updateSearchQuery(textValue)
    }

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue -> textValue = newValue },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        label = { Text("Search movies...") },
        placeholder = { Text("Enter movie title") },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Text("🔍", style = MaterialTheme.typography.titleMedium)
        },
        trailingIcon = {
            if (textValue.isNotEmpty()) {
                Text(
                    "✕",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clickable { textValue = "" }
                )
            }
        },

        )
}