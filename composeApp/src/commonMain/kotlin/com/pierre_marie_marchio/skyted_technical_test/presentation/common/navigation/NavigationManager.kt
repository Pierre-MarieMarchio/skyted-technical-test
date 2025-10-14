package com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation

import com.pierre_marie_marchio.skyted_technical_test.presentation.common.interfaces.Navigator
import com.pierre_marie_marchio.skyted_technical_test.presentation.common.services.Observable

class NavigationManager : Navigator {
    private val _navigationStack = mutableListOf<Screen>(Screen.MovieList())
    val currentScreen = Observable<Screen>(Screen.MovieList())

    override fun navigate(screen: Screen) {
        _navigationStack.add(screen)
        currentScreen.value = screen
    }

    override fun navigateBack() {
        if (_navigationStack.size > 1) {
            _navigationStack.removeLast()
            currentScreen.value = _navigationStack.last()
        }
    }

    override fun canGoBack(): Boolean = _navigationStack.size > 1
}