package com.maku.edvorarides.core.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ride")
data class RideResponseItem(
    val city: String,
    val date: String,
    val destination_station_code: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val map_url: String,
    val origin_station_code: Int,
    val state: String,
    val station_path: List<Int>,
    val distance: Int
)