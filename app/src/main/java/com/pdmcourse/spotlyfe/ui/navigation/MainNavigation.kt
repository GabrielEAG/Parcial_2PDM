package com.pdmcourse.spotlyfe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmcourse.spotlyfe.ui.screens.AddPlace.AddPlaceScreen
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesScreen

@Composable
fun MainNavigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Screen.SavedPlacesScreenNavigation.route) {
    composable(Screen.SavedPlacesScreenNavigation.route) {
      SavedPlacesScreen(navController = navController)
    }
    composable(Screen.AddPlaceScreenNavigation.route) {
      AddPlaceScreen(navController = navController)
    }
  }
}