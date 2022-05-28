package com.maku.edvorarides.core.data.local.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maku.edvorarides.core.data.local.models.RideResponseItem

class RideConvertor {
    var gson = Gson()

    @TypeConverter
    fun rideToString(rideResponseItem: List<RideResponseItem>): String{
        return gson.toJson(rideResponseItem)
    }
    @TypeConverter
    fun stringToRide(data: String): List<RideResponseItem>{
        val listType = object: TypeToken<List<RideResponseItem>>(){}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun intListToString(intList: List<Int>): String{
        return gson.toJson(intList)
    }
    @TypeConverter
    fun stringToIntList(data: String): List<Int>{
        val listType = object: TypeToken<List<Int>>(){}.type
        return gson.fromJson(data, listType)
    }

}
