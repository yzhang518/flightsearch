/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    fun getAllStream(): Flow<List<Airport>>
    //fun getRecommendationStream(userInput: String): Flow<List<Airport>>
    //test 0816
    fun getRecommendationStream(userInput: String): Flow<List<Airport>>

    fun getArrivalStream(id: Int): Flow<List<Airport>>

    fun getFavStream(): Flow<List<Favorite>>

    fun getAirportByCodeStream(code: String): Flow<Airport>

    suspend fun addFavStream(favorite: Favorite)

    suspend fun removeFavStream(favorite: Favorite)


}