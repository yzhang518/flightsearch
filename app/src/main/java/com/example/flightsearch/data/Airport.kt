/* Assignment 5
    Ying Zhang, zhangyin@oregonstate.edu
    CS 492, Oregon State University
 */
package com.example.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "iata_code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "passengers") val passengers: Int

)
