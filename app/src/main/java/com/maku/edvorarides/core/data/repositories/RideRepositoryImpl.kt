package com.maku.edvorarides.core.data.repositories

import android.util.Log
import com.maku.edvorarides.core.data.local.cache.Cache
import com.maku.edvorarides.core.data.local.models.RideResponse
import com.maku.edvorarides.core.data.local.models.RideResponseItem
import com.maku.edvorarides.core.data.remote.RidesApi
import io.reactivex.Flowable
import javax.inject.Inject

class RideRepositoryImpl @Inject constructor(
                         private val api: RidesApi,
                         private val cache: Cache
)
    : RideRepository {
    override fun getRides(): Flowable<List<RideResponseItem>> {
        return cache.getAllRides()
    }

    override suspend fun requestRideNetworkData(): RideResponse {
        // TODO: handle network exceptions here
       return api.getAllRides()
    }

    override suspend fun storeRides(rides: List<RideResponseItem>) {
        cache.storeRides(rides)
    }

    override suspend fun updateRide(distance: String, id: Int) {
        cache.updateRide(distance, id)
    }


}