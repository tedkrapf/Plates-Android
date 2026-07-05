package com.plates.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.plates.app.data.DatabaseHelper

class CreateTripActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_trip)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "New Road Trip"

        val db = DatabaseHelper(this)

        findViewById<Button>(R.id.btnCreateTrip).setOnClickListener {
            val name = findViewById<EditText>(R.id.etTripName).text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a trip name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val description = findViewById<EditText>(R.id.etDescription).text.toString().trim()
            val start = findViewById<EditText>(R.id.etStartLocation).text.toString().trim()
            val end = findViewById<EditText>(R.id.etEndLocation).text.toString().trim()
            val tripId = db.insertTrip(name, description, start, end)
            startActivity(Intent(this, GameActivity::class.java).putExtra("TRIP_ID", tripId))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}
