package com.maku.edvorarides.core.data.repositories

import com.maku.edvorarides.core.data.local.models.RideResponse
import com.maku.edvorarides.core.data.local.models.RideResponseItem
import io.reactivex.Flowable

interface RideRepository {

  fun getRides(): Flowable<List<RideResponseItem>>

  suspend fun requestRideNetworkData(): RideResponse

  suspend fun storeRides(rides: List<RideResponseItem>)

  suspend fun updateRide(distance: String, id: Int)

}