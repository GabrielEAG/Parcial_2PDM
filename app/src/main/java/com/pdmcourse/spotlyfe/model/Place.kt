package com.pdmcourse.spotlyfe.model

import com.google.android.gms.maps.model.LatLng

data class Place(
  val id: Long = 0,
  val name: String,
  val description: String?,
  val latitude: Double,
  val longitude: Double
) {
  fun toLatLng(): LatLng {
    return LatLng(latitude, longitude)
  }
}