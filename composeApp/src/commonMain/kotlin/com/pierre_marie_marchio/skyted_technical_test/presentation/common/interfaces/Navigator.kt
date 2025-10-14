package com.pierre_marie_marchio.skyted_technical_test.presentation.common.interfaces

import com.pierre_marie_marchio.skyted_technical_test.presentation.common.navigation.Screen

interface Navigator {
    fun navigate(screen: Screen)
    fun navigateBack()
    fun canGoBack(): Boolean
}