package com.plates.app.data

import kotlinx.coroutines.flow.Flow

class PlatesRepository(private val db: AppDatabase) {

    fun getAllTripSummaries(): Flow<List<RoadTripSummary>> =
        db.roadTripDao().getAllTripSummaries()

    fun getSpottedPlatesForTrip(tripId: Long): Flow<List<SpottedPlate>> =
        db.spottedPlateDao().getSpottedPlatesForTrip(tripId)

    suspend fun insertTrip(trip: RoadTrip): Long =
        db.roadTripDao().insertTrip(trip)

    suspend fun updateTrip(trip: RoadTrip) =
        db.roadTripDao().updateTrip(trip)

    suspend fun deleteTrip(trip: RoadTrip) =
        db.roadTripDao().deleteTrip(trip)

    suspend fun getTripById(id: Long): RoadTrip? =
        db.roadTripDao().getTripById(id)

    suspend fun insertSpottedPlate(plate: SpottedPlate) =
        db.spottedPlateDao().insertSpottedPlate(plate)

    suspend fun deleteSpottedPlate(tripId: Long, stateCode: String) =
        db.spottedPlateDao().deleteSpottedPlate(tripId, stateCode)
}
