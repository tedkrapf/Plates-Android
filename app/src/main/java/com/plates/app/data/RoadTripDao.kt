package com.plates.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RoadTripDao {

    @Query("""
        SELECT r.*, COUNT(s.id) as spotted_count
        FROM road_trips r
        LEFT JOIN spotted_plates s ON r.id = s.trip_id
        GROUP BY r.id
        ORDER BY r.created_at DESC
    """)
    fun getAllTripSummaries(): Flow<List<RoadTripSummary>>

    @Query("SELECT * FROM road_trips WHERE id = :id")
    suspend fun getTripById(id: Long): RoadTrip?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: RoadTrip): Long

    @Update
    suspend fun updateTrip(trip: RoadTrip)

    @Delete
    suspend fun deleteTrip(trip: RoadTrip)
}
