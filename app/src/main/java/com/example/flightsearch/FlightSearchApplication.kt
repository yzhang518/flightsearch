/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch

import android.app.Application
import com.example.flightsearch.data.AppContainer
import com.example.flightsearch.data.AppDataContainer


class FlightSearchApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}