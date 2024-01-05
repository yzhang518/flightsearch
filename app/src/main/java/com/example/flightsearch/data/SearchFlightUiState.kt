/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

data class SearchFlightUiState(
    //val id: Int = 0,
    val searchBy: String = "",
    val selectedAirports: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList(),
    val departAirport: Airport = Airport(0, "", "", 0),
    val arriveAirport: Airport = Airport(0, "", "", 0)
)
