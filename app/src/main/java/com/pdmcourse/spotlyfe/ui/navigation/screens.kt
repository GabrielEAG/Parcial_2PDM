package com.pdmcourse.spotlyfe.ui.navigation

sealed class Screen(val route: String) {
    object SavedPlacesScreenNavigation : Screen("saved_places_screen")
    object AddPlaceScreenNavigation : Screen("add_place_screen")
}