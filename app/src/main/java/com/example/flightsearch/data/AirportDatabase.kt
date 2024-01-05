/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class AirportDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao

    companion object {
        @Volatile
        private var Instance: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase {
            return Instance?: synchronized(this) {
                Room.databaseBuilder(context, AirportDatabase::class.java, "airport_database")
                    .createFromAsset("database/flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}