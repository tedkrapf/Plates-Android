package com.plates.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class RoadTrip(
    val id: Long,
    val name: String,
    val description: String,
    val startLocation: String,
    val endLocation: String,
    val createdAt: Long,
    val isActive: Boolean,
    val spottedCount: Int = 0
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "plates.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE road_trips (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                description TEXT DEFAULT '',
                start_location TEXT DEFAULT '',
                end_location TEXT DEFAULT '',
                created_at INTEGER NOT NULL,
                is_active INTEGER DEFAULT 1
            )
        """)
        db.execSQL("""
            CREATE TABLE spotted_plates (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                trip_id INTEGER NOT NULL,
                state_code TEXT NOT NULL,
                spotted_at INTEGER NOT NULL,
                UNIQUE(trip_id, state_code),
                FOREIGN KEY(trip_id) REFERENCES road_trips(id) ON DELETE CASCADE
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    override fun onConfigure(db: SQLiteDatabase) {
        db.setForeignKeyConstraintsEnabled(true)
    }

    fun insertTrip(name: String, description: String, start: String, end: String): Long {
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("description", description)
        cv.put("start_location", start)
        cv.put("end_location", end)
        cv.put("created_at", System.currentTimeMillis())
        cv.put("is_active", 1)
        return writableDatabase.insert("road_trips", null, cv)
    }

    fun getAllTrips(): List<RoadTrip> {
        val trips = mutableListOf<RoadTrip>()
        val cursor: Cursor = readableDatabase.rawQuery("""
            SELECT r.id, r.name, r.description, r.start_location, r.end_location,
                   r.created_at, r.is_active, COUNT(s.id) as spotted_count
            FROM road_trips r
            LEFT JOIN spotted_plates s ON r.id = s.trip_id
            GROUP BY r.id
            ORDER BY r.created_at DESC
        """, null)
        cursor.use {
            while (it.moveToNext()) {
                trips.add(RoadTrip(
                    id = it.getLong(0),
                    name = it.getString(1),
                    description = it.getString(2),
                    startLocation = it.getString(3),
                    endLocation = it.getString(4),
                    createdAt = it.getLong(5),
                    isActive = it.getInt(6) == 1,
                    spottedCount = it.getInt(7)
                ))
            }
        }
        return trips
    }

    fun getTripById(id: Long): RoadTrip? {
        val cursor = readableDatabase.rawQuery(
            "SELECT id, name, description, start_location, end_location, created_at, is_active FROM road_trips WHERE id = ?",
            arrayOf(id.toString())
        )
        return cursor.use {
            if (it.moveToFirst()) RoadTrip(
                id = it.getLong(0),
                name = it.getString(1),
                description = it.getString(2),
                startLocation = it.getString(3),
                endLocation = it.getString(4),
                createdAt = it.getLong(5),
                isActive = it.getInt(6) == 1
            ) else null
        }
    }

    fun endTrip(id: Long) {
        val cv = ContentValues()
        cv.put("is_active", 0)
        writableDatabase.update("road_trips", cv, "id = ?", arrayOf(id.toString()))
    }

    fun deleteTrip(id: Long) {
        writableDatabase.delete("road_trips", "id = ?", arrayOf(id.toString()))
    }

    fun getSpottedCodes(tripId: Long): Set<String> {
        val codes = mutableSetOf<String>()
        val cursor = readableDatabase.rawQuery(
            "SELECT state_code FROM spotted_plates WHERE trip_id = ?",
            arrayOf(tripId.toString())
        )
        cursor.use { while (it.moveToNext()) codes.add(it.getString(0)) }
        return codes
    }

    fun spotPlate(tripId: Long, stateCode: String) {
        val cv = ContentValues()
        cv.put("trip_id", tripId)
        cv.put("state_code", stateCode)
        cv.put("spotted_at", System.currentTimeMillis())
        writableDatabase.insertWithOnConflict("spotted_plates", null, cv, SQLiteDatabase.CONFLICT_IGNORE)
    }

    fun unspotPlate(tripId: Long, stateCode: String) {
        writableDatabase.delete("spotted_plates",
            "trip_id = ? AND state_code = ?",
            arrayOf(tripId.toString(), stateCode))
    }
}
