package com.plates.app.data

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class RoadTripSummary(
    @Embedded val trip: RoadTrip,
    @ColumnInfo(name = "spotted_count") val spottedCount: Int
)
