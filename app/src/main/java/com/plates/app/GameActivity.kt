package com.plates.app

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.TextView
import com.plates.app.data.DatabaseHelper
import com.plates.app.data.PlateData
import com.plates.app.ui.PlateListAdapter

class GameActivity : Activity() {

    private lateinit var db: DatabaseHelper
    private lateinit var tvScore: TextView
    private lateinit var tvProgress: TextView
    private var tripId = -1L
    private val spotted = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        tripId = intent.getLongExtra("TRIP_ID", -1L)
        if (tripId == -1L) { finish(); return }

        db = DatabaseHelper(this)
        spotted.addAll(db.getSpottedCodes(tripId))

        val trip = db.getTripById(tripId)
        actionBar?.title = trip?.name ?: "Road Trip"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        tvScore = findViewById(R.id.tvScore)
        tvProgress = findViewById(R.id.tvProgress)

        val sections = listOf(
            "United States  (${PlateData.usStates.size} states)" to PlateData.usStates,
            "Canada  (${PlateData.canadianProvinces.size} provinces/territories)" to PlateData.canadianProvinces
        )

        val adapter = PlateListAdapter(
            context = this,
            sections = sections,
            spottedCodes = spotted,
            onToggle = { code, isSpotted ->
                if (isSpotted) db.spotPlate(tripId, code) else db.unspotPlate(tripId, code)
            },
            onScoreChanged = { updateScore() }
        )

        findViewById<ListView>(R.id.listPlates).adapter = adapter
        updateScore()
    }

    private fun updateScore() {
        val count = spotted.size
        val total = PlateData.totalCount
        val pct = if (total > 0) count * 100 / total else 0
        tvScore.text = "$count / $total plates"
        tvProgress.text = "$pct%"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 1, 0, "End Trip")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { finish(); true }
            1 -> { showEndTripDialog(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showEndTripDialog() {
        AlertDialog.Builder(this)
            .setTitle("End Trip?")
            .setMessage("Mark this trip as completed? You can still view it later.")
            .setPositiveButton("End Trip") { _, _ -> db.endTrip(tripId); finish() }
            .setNegativeButton("Keep Going", null)
            .show()
    }
}
