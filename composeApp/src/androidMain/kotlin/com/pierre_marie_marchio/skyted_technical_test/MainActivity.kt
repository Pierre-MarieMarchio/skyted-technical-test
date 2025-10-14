package com.pierre_marie_marchio.skyted_technical_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pierre_marie_marchio.skyted_technical_test.presentation.App
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.NavigationManager

class MainActivity : ComponentActivity() {

    private val navigationManager = NavigationManager()
    private val movieDetailsVM by lazy { DependencyContainer.movieDetailsVM }
    private val movieListVM by lazy { DependencyContainer.movieListVM }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                navigationManager = navigationManager,
                movieListVM = movieListVM,
                movieDetailsVM = movieDetailsVM
            )
        }
    }
}