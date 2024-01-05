/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data


data class DisplayFlightUiState(
    val id: Int = 0,
    val departCode: String = "",
    val departName: String = "",
    val arrivalAirportList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList()
)
