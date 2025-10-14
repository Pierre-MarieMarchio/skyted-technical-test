package com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.NavigationManager
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.Screen
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieDetailsVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.interfaces.MovieListVM
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen.HomeScreen
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen.MovieDetailsScreen
import com.pierre_marie_marchio.skyted_technical_test.presentation.movieexplorer.views.screen.MovieListScreen

@Composable
fun MovieExplorerNavigation(
    currentScreen: Screen,
    movieListVM: MovieListVM,
    movieDetailsVM: MovieDetailsVM,
    navigator: NavigationManager,
    modifier: Modifier = Modifier
) {

    when (currentScreen) {
        is Screen.MovieList -> {
            MovieListScreen(
                viewModel = movieListVM,
                onMovieClick = { movieId ->
                    navigator.navigate(Screen.MovieDetails(movieId))
                },
                modifier = modifier
            )
        }

        is Screen.MovieDetails -> {
            MovieDetailsScreen(
                viewModel = movieDetailsVM,
                movieId = currentScreen.movieId,
                onBackClick = { navigator.navigateBack() },
                modifier = modifier
            )
        }

        is Screen.HomeScreen -> {
            HomeScreen(
                modifier = modifier,
                onNavigateToMovieList = { navigator.navigate(Screen.MovieList()) }
            )
        }
    }
}