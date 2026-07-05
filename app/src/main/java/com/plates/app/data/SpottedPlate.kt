package com.plates.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "spotted_plates",
    foreignKeys = [
        ForeignKey(
            entity = RoadTrip::class,
            parentColumns = ["id"],
            childColumns = ["trip_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["trip_id"]),
        Index(value = ["trip_id", "state_code"], unique = true)
    ]
)
data class SpottedPlate(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "trip_id") val tripId: Long,
    @ColumnInfo(name = "state_code") val stateCode: String,
    @ColumnInfo(name = "spotted_at") val spottedAt: Long = System.currentTimeMillis()
)
