package com.pierre_marie_marchio.skyted_technical_test.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.NavigationManager
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.Screen
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.navigation.MovieExplorerNavigation

@Composable
fun App(
    navigationManager: NavigationManager,
    movieListVM: MovieListVM,
    movieDetailsVM: MovieDetailsVM
) {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.MovieList()) }

        LaunchedEffect(navigationManager) {
            navigationManager.currentScreen.observe { screen ->
                currentScreen = screen
            }
        }

        when (val screen = currentScreen) {
            is Screen.MovieList,
            is Screen.MovieDetails -> {
                MovieExplorerNavigation(
                    currentScreen = screen,
                    movieListVM = movieListVM,
                    movieDetailsVM = movieDetailsVM,
                    navigator = navigationManager
                )
            }

        }

    }
}