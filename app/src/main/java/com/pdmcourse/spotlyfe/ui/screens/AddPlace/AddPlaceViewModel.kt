// ui/screens/AddPlace/AddPlaceViewModel.kt
package com.pdmcourse.spotlyfe.ui.screens.AddPlace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.pdmcourse.spotlyfe.data.repository.PlaceRepository
import com.pdmcourse.spotlyfe.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlaceViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel() {

    private val _placeName = MutableStateFlow("")
    val placeName: StateFlow<String> = _placeName.asStateFlow()

    private val _placeDescription = MutableStateFlow("")
    val placeDescription: StateFlow<String> = _placeDescription.asStateFlow()

    // La posición inicial por defecto para el mapa de selección
    private val defaultInitialLocation = LatLng(13.679244047895181, -89.2357873899347)

    private val _selectedLocation = MutableStateFlow<LatLng?>(defaultInitialLocation) // Inicializa con una ubicación por defecto
    val selectedLocation: StateFlow<LatLng?> = _selectedLocation.asStateFlow()

    fun updatePlaceName(name: String) {
        _placeName.value = name
    }

    fun updatePlaceDescription(description: String) {
        _placeDescription.value = description
    }

    fun updateSelectedLocation(latLng: LatLng) {
        _selectedLocation.value = latLng
    }

    fun savePlace(onPlaceSaved: () -> Unit) {
        viewModelScope.launch {
            val name = _placeName.value
            val description = _placeDescription.value.ifEmpty { null }
            val location = _selectedLocation.value

            if (name.isNotEmpty() && location != null) {
                val newPlace = Place(
                    name = name,
                    description = description,
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                placeRepository.insertPlace(newPlace)
                onPlaceSaved()
            }
        }
    }
}