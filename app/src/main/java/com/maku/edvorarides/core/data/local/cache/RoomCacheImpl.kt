package com.maku.edvorarides.core.data.local.cache

import com.maku.edvorarides.core.data.local.dao.RidesDao
import com.maku.edvorarides.core.data.local.models.RideResponseItem
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCacheImpl @Inject constructor(
    private val ridesDao: RidesDao
    ) : Cache {
    override fun getAllRides(): Flowable<List<RideResponseItem>> {
        return ridesDao.getRides()
    }

    override suspend fun storeRides(rides: List<RideResponseItem>) {
        return ridesDao.insertRides(rides)
    }

    override suspend fun updateRide(distance: String, id: Int) {
        ridesDao.updateRide(distance, id)
    }


}