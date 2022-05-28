package com.maku.edvorarides.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maku.edvorarides.core.data.local.convertors.RideConvertor
import com.maku.edvorarides.core.data.local.dao.RidesDao
import com.maku.edvorarides.core.data.local.models.RideResponse
import com.maku.edvorarides.core.data.local.models.RideResponseItem

@Database(
    entities = [
        RideResponseItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RideConvertor::class)
abstract class RidesDatabase : RoomDatabase() {
    abstract fun ridesDao(): RidesDao
}
