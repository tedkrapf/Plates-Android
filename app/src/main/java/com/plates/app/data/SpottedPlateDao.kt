package com.plates.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpottedPlateDao {

    @Query("SELECT * FROM spotted_plates WHERE trip_id = :tripId ORDER BY spotted_at ASC")
    fun getSpottedPlatesForTrip(tripId: Long): Flow<List<SpottedPlate>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpottedPlate(plate: SpottedPlate)

    @Query("DELETE FROM spotted_plates WHERE trip_id = :tripId AND state_code = :stateCode")
    suspend fun deleteSpottedPlate(tripId: Long, stateCode: String)
}
