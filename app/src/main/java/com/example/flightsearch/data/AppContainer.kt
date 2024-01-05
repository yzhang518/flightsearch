/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import android.content.Context

interface AppContainer {
    val airportRepository: AirportRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        OffLineAirportRepository(AirportDatabase.getDatabase(context).airportDao())
    }
}