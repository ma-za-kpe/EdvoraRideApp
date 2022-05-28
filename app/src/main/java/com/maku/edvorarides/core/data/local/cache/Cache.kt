package com.maku.edvorarides.core.data.local.cache

import com.maku.edvorarides.core.data.local.models.RideResponseItem
import io.reactivex.Flowable

interface Cache {
    fun getAllRides(): Flowable<List<RideResponseItem>>
    suspend fun storeRides(rides: List<RideResponseItem>)
    suspend fun updateRide(distance: String, id: Int)

}