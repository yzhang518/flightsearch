/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class OffLineAirportRepository(private val airportDao: AirportDao): AirportRepository {
    override fun getAllStream(): Flow<List<Airport>> = airportDao.getAll()

    override fun getRecommendationStream(userInput: String): Flow<List<Airport>> = airportDao.getRecommendation(userInput)

    override fun getArrivalStream(id: Int): Flow<List<Airport>> = airportDao.getArrival(id)

    override fun getFavStream(): Flow<List<Favorite>> = airportDao.getFav()

    override fun getAirportByCodeStream(code: String): Flow<Airport> = airportDao.getAirportByCode(code)

    override suspend fun addFavStream(favorite: Favorite) {
        airportDao.addFav(favorite)
    }

    override suspend fun removeFavStream(favorite: Favorite) {
        airportDao.removeFav(favorite)
    }
}
