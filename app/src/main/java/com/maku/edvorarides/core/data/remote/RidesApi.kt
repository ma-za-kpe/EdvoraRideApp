package com.maku.edvorarides.core.data.remote

import com.maku.edvorarides.core.data.local.models.RideResponse
import retrofit2.http.GET

interface RidesApi {
    // get all rides
    @GET("rides")
    suspend fun getAllRides(): RideResponse
}
