package com.pierre_marie_marchio.skyted_technical_test

import androidx.compose.ui.window.ComposeUIViewController
import com.pierre_marie_marchio.skyted_technical_test.presentation.App
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.NavigationManager

fun MainViewController() = ComposeUIViewController {
    App(
        navigationManager = NavigationManager(),
        movieListVM = DependencyContainer.movieListVM,
        movieDetailsVM = DependencyContainer.movieDetailsVM
    )
}