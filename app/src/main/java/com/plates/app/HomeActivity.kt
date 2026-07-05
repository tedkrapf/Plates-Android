package com.plates.app

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import com.plates.app.data.DatabaseHelper
import com.plates.app.data.RoadTrip

class HomeActivity : Activity() {

    private lateinit var db: DatabaseHelper
    private lateinit var adapter: TripAdapter
    private lateinit var listView: ListView
    private lateinit var emptyView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actionBar?.title = "Plates"

        db = DatabaseHelper(this)
        listView = findViewById(R.id.listTrips)
        emptyView = findViewById(R.id.tvEmpty)

        adapter = TripAdapter(this, emptyList())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val trip = adapter.getItem(position)
            val cls = if (trip.isActive) GameActivity::class.java else TripDetailsActivity::class.java
            startActivity(Intent(this, cls).putExtra("TRIP_ID", trip.id))
        }

        registerForContextMenu(listView)
        findViewById<View>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, CreateTripActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadTrips()
    }

    private fun loadTrips() {
        val trips = db.getAllTrips()
        adapter.update(trips)
        emptyView.visibility = if (trips.isEmpty()) View.VISIBLE else View.GONE
        listView.visibility = if (trips.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, info: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, info)
        val pos = (info as? AdapterView.AdapterContextMenuInfo)?.position ?: return
        val trip = adapter.getItem(pos)
        menu.setHeaderTitle(trip.name)
        if (trip.isActive) menu.add(0, 1, 0, "End Trip")
        menu.add(0, 2, 1, "Delete Trip")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val pos = (item.menuInfo as? AdapterView.AdapterContextMenuInfo)?.position
            ?: return super.onContextItemSelected(item)
        val trip = adapter.getItem(pos)
        return when (item.itemId) {
            1 -> { confirmEnd(trip); true }
            2 -> { confirmDelete(trip); true }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun confirmEnd(trip: RoadTrip) {
        AlertDialog.Builder(this)
            .setTitle("End Trip?")
            .setMessage("Mark \"${trip.name}\" as completed?")
            .setPositiveButton("End") { _, _ -> db.endTrip(trip.id); loadTrips() }
            .setNegativeButton("Cancel", null).show()
    }

    private fun confirmDelete(trip: RoadTrip) {
        AlertDialog.Builder(this)
            .setTitle("Delete Trip?")
            .setMessage("Delete \"${trip.name}\" and all its spotted plates?")
            .setPositiveButton("Delete") { _, _ -> db.deleteTrip(trip.id); loadTrips() }
            .setNegativeButton("Cancel", null).show()
    }
}
