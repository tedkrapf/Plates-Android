package com.plates.app

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.plates.app.data.DatabaseHelper
import com.plates.app.data.PlateData
import com.plates.app.ui.PlateListAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TripDetailsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_details)

        val tripId = intent.getLongExtra("TRIP_ID", -1L)
        if (tripId == -1L) { finish(); return }

        val db = DatabaseHelper(this)
        val trip = db.getTripById(tripId) ?: run { finish(); return }
        val spotted = db.getSpottedCodes(tripId)

        actionBar?.title = trip.name
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val fmt = SimpleDateFormat("MMMM d, yyyy", Locale.US)
        findViewById<TextView>(R.id.tvDetailName).text = trip.name
        findViewById<TextView>(R.id.tvDetailDate).text = fmt.format(Date(trip.createdAt))

        val parts = mutableListOf<String>()
        if (trip.startLocation.isNotEmpty()) parts.add(trip.startLocation)
        if (trip.endLocation.isNotEmpty()) parts.add(trip.endLocation)
        if (parts.isNotEmpty()) {
            val locView = findViewById<TextView>(R.id.tvDetailLocations)
            locView.text = parts.joinToString(" → ")
            locView.visibility = View.VISIBLE
        }

        if (trip.description.isNotEmpty()) {
            val descView = findViewById<TextView>(R.id.tvDetailDescription)
            descView.text = trip.description
            descView.visibility = View.VISIBLE
        }

        val total = PlateData.totalCount
        val pct = if (total > 0) spotted.size * 100 / total else 0
        val status = if (trip.isActive) " (Active)" else ""
        findViewById<TextView>(R.id.tvDetailScore).text =
            "${spotted.size} / $total plates spotted  ($pct%)$status"

        val spottedPlates = PlateData.all.filter { it.code in spotted }
        val missedPlates = PlateData.all.filter { it.code !in spotted }

        val sections = mutableListOf<Pair<String, List<com.plates.app.data.PlateInfo>>>()
        if (spottedPlates.isNotEmpty()) {
            sections.add("Spotted  (${spottedPlates.size})" to spottedPlates)
        }
        if (missedPlates.isNotEmpty()) {
            sections.add("Not Spotted  (${missedPlates.size})" to missedPlates)
        }

        val adapter = PlateListAdapter(
            context = this,
            sections = sections,
            spottedCodes = spotted.toMutableSet(),
            onToggle = { _, _ -> },
            onScoreChanged = {},
            readOnly = true
        )

        findViewById<ListView>(R.id.listSpottedPlates).adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}
