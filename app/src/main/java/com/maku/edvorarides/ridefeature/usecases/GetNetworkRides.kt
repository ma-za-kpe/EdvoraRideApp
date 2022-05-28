package com.maku.edvorarides.ridefeature.usecases

import com.maku.edvorarides.core.data.local.models.RideResponseItem
import com.maku.edvorarides.core.data.repositories.RideRepository
import javax.inject.Inject

class GetNetworkRides @Inject constructor(
    private val repo: RideRepository
) {
    suspend operator fun invoke(): List<RideResponseItem> {

        val rides = repo.requestRideNetworkData()

        // caching happens here
        repo.storeRides(rides)
        return rides
    }}