/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport")
    fun getAll(): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE name LIKE '%' || :userInput || '%'" +
            "OR iata_code LIKE '%' || :userInput || '%'" +
            "ORDER BY passengers DESC")
    fun getRecommendation(userInput: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE id != :id")
    fun getArrival(id: Int): Flow<List<Airport>>

    @Query("SELECT * FROM favorite")
    fun getFav(): Flow<List<Favorite>>

    @Query("SELECT * FROM airport WHERE iata_code == :code")
    fun getAirportByCode(code: String): Flow<Airport>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(favorite: Favorite)

    @Delete
    suspend fun removeFav(favorite: Favorite)

}