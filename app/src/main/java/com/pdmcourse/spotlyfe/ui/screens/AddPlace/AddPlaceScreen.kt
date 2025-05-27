package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.pdmcourse.spotlyfe.ui.components.SelectLocationMap
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaceScreen(
    navController: NavController,
    viewModel: AddPlaceViewModel = hiltViewModel()
) {
    val placeName by viewModel.placeName.collectAsState()
    val placeDescription by viewModel.placeDescription.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Agregar Nuevo Lugar",
                canGoBack = true,
                onBackClicked = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = placeName,
                onValueChange = { viewModel.updatePlaceName(it) },
                label = { Text("Nombre del Lugar *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = placeDescription,
                onValueChange = { viewModel.updatePlaceDescription(it) },
                label = { Text("Descripción (Opcional)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Selecciona una ubicación en el mapa:", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))

            SelectLocationMap(
                initialPosition = selectedLocation ?: LatLng(13.679244047895181, -89.2357873899347),
                onLocationChanged = { latLng ->
                    viewModel.updateSelectedLocation(latLng)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.savePlace {
                        navController.popBackStack()
                    }
                },
                enabled = placeName.isNotBlank() && selectedLocation != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Lugar")
            }
        }
    }
}