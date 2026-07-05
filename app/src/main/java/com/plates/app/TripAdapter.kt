package com.plates.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.plates.app.data.RoadTrip
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TripAdapter(
    private val context: Context,
    private var trips: List<RoadTrip>
) : BaseAdapter() {

    private val fmt = SimpleDateFormat("MMM d, yyyy", Locale.US)

    override fun getCount() = trips.size
    override fun getItem(position: Int): RoadTrip = trips[position]
    override fun getItemId(position: Int) = trips[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false)
        val trip = trips[position]
        view.findViewById<TextView>(R.id.tvTripName).text = trip.name
        view.findViewById<TextView>(R.id.tvTripDate).text = fmt.format(Date(trip.createdAt))
        view.findViewById<TextView>(R.id.tvTripScore).text = "${trip.spottedCount} plates"
        val statusView = view.findViewById<TextView>(R.id.tvTripStatus)
        if (trip.isActive) {
            statusView.text = "ACTIVE"
            statusView.setTextColor(0xFF1B7A3A.toInt())
        } else {
            statusView.text = "DONE"
            statusView.setTextColor(0xFF888888.toInt())
        }
        return view
    }

    fun update(newTrips: List<RoadTrip>) {
        trips = newTrips
        notifyDataSetChanged()
    }
}
