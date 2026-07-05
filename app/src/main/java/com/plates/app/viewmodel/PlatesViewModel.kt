package com.plates.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.plates.app.PlatesApplication
import com.plates.app.data.PlatesRepository
import com.plates.app.data.RoadTrip
import com.plates.app.data.RoadTripSummary
import com.plates.app.data.SpottedPlate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlatesViewModel(private val repository: PlatesRepository) : ViewModel() {

    val tripSummaries: StateFlow<List<RoadTripSummary>> = repository
        .getAllTripSummaries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _tripCreatedId = MutableSharedFlow<Long>(extraBufferCapacity = 1)
    val tripCreatedId = _tripCreatedId.asSharedFlow()

    fun createTrip(
        name: String,
        description: String,
        startLocation: String,
        endLocation: String
    ) {
        viewModelScope.launch {
            val id = repository.insertTrip(
                RoadTrip(
                    name = name.trim(),
                    description = description.trim(),
                    startLocation = startLocation.trim(),
                    endLocation = endLocation.trim()
                )
            )
            _tripCreatedId.emit(id)
        }
    }

    fun getSpottedCodes(tripId: Long): Flow<Set<String>> =
        repository.getSpottedPlatesForTrip(tripId).map { list ->
            list.map { it.stateCode }.toSet()
        }

    fun getSpottedPlates(tripId: Long): Flow<List<SpottedPlate>> =
        repository.getSpottedPlatesForTrip(tripId)

    fun togglePlate(tripId: Long, stateCode: String, currentlySpotted: Boolean) {
        viewModelScope.launch {
            if (currentlySpotted) {
                repository.deleteSpottedPlate(tripId, stateCode)
            } else {
                repository.insertSpottedPlate(SpottedPlate(tripId = tripId, stateCode = stateCode))
            }
        }
    }

    fun endTrip(tripId: Long) {
        viewModelScope.launch {
            val trip = repository.getTripById(tripId) ?: return@launch
            repository.updateTrip(trip.copy(isActive = false))
        }
    }

    fun deleteTrip(tripId: Long) {
        viewModelScope.launch {
            val trip = repository.getTripById(tripId) ?: return@launch
            repository.deleteTrip(trip)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PlatesApplication
                PlatesViewModel(app.repository)
            }
        }
    }
}
