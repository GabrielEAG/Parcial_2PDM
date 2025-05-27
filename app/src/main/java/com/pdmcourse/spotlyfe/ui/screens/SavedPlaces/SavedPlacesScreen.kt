package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pdmcourse.spotlyfe.ui.navigation.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar

@Composable
fun SavedPlacesScreen(
  navController: NavController,
  viewModel: SavedPlacesViewModel = hiltViewModel()
) {
  val places by viewModel.places.collectAsState()

  val initialMapPosition = LatLng(13.679244047895181, -89.2357873899347) // Posición inicial del mapa (puedes ajustar)
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(initialMapPosition, 10f)
  }

  Scaffold(
    topBar = {
      CustomTopBar(title = "SpotLyfe - Tus Lugares Favoritos")
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          navController.navigate(Screen.AddPlaceScreenNavigation.route)
        }
      ) {
        Icon(Icons.Filled.Add, "Agregar nuevo lugar")
      }
    }
  ) { paddingValues ->
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
      ) {
        places.forEach { place ->
          Marker(
            state = MarkerState(position = LatLng(place.latitude, place.longitude)),
            title = place.name,
            snippet = place.description
          )
        }
      }
    }
  }
}