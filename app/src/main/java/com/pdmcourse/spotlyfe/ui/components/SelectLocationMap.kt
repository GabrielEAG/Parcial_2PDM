package com.pdmcourse.spotlyfe.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SelectLocationMap(
    initialPosition: LatLng = LatLng(13.679244047895181, -89.2357873899347), // Posición inicial dada
    onLocationChanged: (LatLng) -> Unit // Callback para cuando la ubicación cambie
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 16f) // Zoom ajustado
    }

    var markerState by remember { mutableStateOf(MarkerState(position = initialPosition)) }

    // Actualiza el marcador si initialPosition cambia (ej. si se pasa un lugar ya guardado para editar)
    LaunchedEffect(initialPosition) {
        markerState = MarkerState(position = initialPosition)
        // También podrías mover la cámara si lo deseas:
        // cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(initialPosition, 16f))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(300.dp) // Altura fija para el mapa
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { newLatLng ->
                markerState = MarkerState(position = newLatLng)
                onLocationChanged(newLatLng)
                println("SelectLocationMap: New location selected: $newLatLng")
            }
        ) {
            Marker(state = markerState)
        }
    }
}