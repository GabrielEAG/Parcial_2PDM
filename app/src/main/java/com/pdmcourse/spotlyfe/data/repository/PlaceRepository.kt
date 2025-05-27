package com.pdmcourse.spotlyfe.data.repository

import com.pdmcourse.spotlyfe.data.database.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.toEntity
import com.pdmcourse.spotlyfe.data.database.entities.toDomain
import com.pdmcourse.spotlyfe.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepository @Inject constructor(
    private val placeDao: PlaceDao
) {

    fun getAllPlaces(): Flow<List<Place>> {
        return placeDao.getAllPlaces().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun insertPlace(place: Place) {
        placeDao.insertPlace(place.toEntity())
    }
}