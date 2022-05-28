package com.maku.edvorarides.ridefeature.usecases

import com.maku.edvorarides.core.data.repositories.RideRepository
import javax.inject.Inject

class GetCachedRides @Inject constructor(
    private val repo: RideRepository
) {
    operator fun invoke() = repo.getRides()
}