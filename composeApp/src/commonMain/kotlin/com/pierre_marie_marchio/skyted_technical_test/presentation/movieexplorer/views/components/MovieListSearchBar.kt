package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import kotlinx.coroutines.delay

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
        onValueChange = { newValue ->
            textValue = newValue
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text("Search movies...") },
        placeholder = { Text("Enter movie title") },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),

        leadingIcon = {
            Text("🔍", style = MaterialTheme.typography.titleMedium)
        },

        trailingIcon = {
            if (textValue.isNotEmpty()) {
                IconButton(onClick = { textValue = "" }) {
                    Text("✕")
                }
            }
        }
    )
}