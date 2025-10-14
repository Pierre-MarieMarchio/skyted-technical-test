package com.pierre_marie_marchio.skyted_technical_test.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.NavigationManager
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.Screen
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.ui.theme.AppTheme
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.navigation.MovieExplorerNavigation

@Composable
fun App(
    navigationManager: NavigationManager,
    movieListVM: MovieListVM,
    movieDetailsVM: MovieDetailsVM
) {
    AppTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.HomeScreen) }

        val gradient = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        )


        LaunchedEffect(navigationManager) {
            navigationManager.currentScreen.observe { screen ->
                currentScreen = screen
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            contentWindowInsets = WindowInsets.systemBars
        ) { padding ->
            when (val screen = currentScreen) {
                is Screen.HomeScreen,
                is Screen.MovieList,
                is Screen.MovieDetails -> {
                    MovieExplorerNavigation(
                        currentScreen = screen,
                        movieListVM = movieListVM,
                        movieDetailsVM = movieDetailsVM,
                        navigator = navigationManager,
                        modifier = Modifier
                            .background(gradient)
                            .padding(padding)
                    )
                }
            }
        }

    }
}