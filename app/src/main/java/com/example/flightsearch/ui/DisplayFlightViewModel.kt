/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.DisplayFlightUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.flightsearch.data.AirportRepository
import com.example.flightsearch.data.Favorite
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DisplayFlightViewModel(airportRepository: AirportRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DisplayFlightUiState())
    val uiState: StateFlow<DisplayFlightUiState> = _uiState.asStateFlow()

    fun setDepartureCode(departCode: String) {
        _uiState.update { currentState ->
            currentState.copy(departCode = departCode)
        }
    }

    fun setDepartureName(departName: String) {
        _uiState.update { currentState ->
            currentState.copy(departName = departName)
        }
    }

    private var temRepo = airportRepository

    fun setArrival(departId: Int) {
        viewModelScope.launch {
            val arrivalAirportList = temRepo.getArrivalStream(departId).first() // Collect initial value
            _uiState.update { currentState ->
                currentState.copy(arrivalAirportList = arrivalAirportList)
            }
        }
    }

    fun addFavorite(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            temRepo.addFavStream(
                Favorite(
                    departure = departureCode,
                    destination = destinationCode
                )
            )
        }
    }
/*
    fun removeFavorite(favorite: Favorite) {
        viewModelScope.launch {
            temRepo.removeFavStream(favorite)
        }
    }
    */
}