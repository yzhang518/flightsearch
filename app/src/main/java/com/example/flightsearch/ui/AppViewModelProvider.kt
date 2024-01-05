/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SearchFlightViewModel(
                flightSearchApplication().container.airportRepository
            )
        }

        initializer {
            DisplayFlightViewModel(
                flightSearchApplication().container.airportRepository
            )
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)

