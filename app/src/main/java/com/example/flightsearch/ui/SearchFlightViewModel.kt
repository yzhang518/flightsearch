/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.SearchFlightUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.flightsearch.data.AirportRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchFlightViewModel(airportRepository: AirportRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchFlightUiState())
    val uiState: StateFlow<SearchFlightUiState> = _uiState.asStateFlow()

    fun updateQuery(input: String) {
        _uiState.update { currentState ->
            currentState.copy(searchBy = input)
        }
        // update selectedAirports right after userInput changes
        updateSelectedAirports(input)
    }

    private var temRepo = airportRepository

    private fun updateSelectedAirports(userInput: String) {
        if (userInput.isNotEmpty()) {
            viewModelScope.launch {
                val selectedAirportList = temRepo.getRecommendationStream(userInput).first() // Collect initial value
                _uiState.update { currentState ->
                    currentState.copy(selectedAirports = selectedAirportList)
                }
            }
        } else {
            // Clear the selected airports when userInput is empty
            _uiState.update { currentState ->
                currentState.copy(selectedAirports = emptyList())
            }
            // get fav list
            viewModelScope.launch {
                val favList = temRepo.getFavStream().first() // Collect initial value
                _uiState.update { currentState ->
                    currentState.copy(favoriteList = favList)
                }
            }
        }
    }
}



