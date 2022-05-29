package com.maku.edvorarides.core.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ride")
data class RideResponseItem(
    @ColumnInfo val city: String,
    @ColumnInfo val date: String,
    @ColumnInfo val destination_station_code: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Int,
    @ColumnInfo val map_url: String,
    @ColumnInfo val origin_station_code: Int,
    @ColumnInfo val state: String,
    @ColumnInfo val station_path: List<Int>,
    @ColumnInfo val distance: Int
)