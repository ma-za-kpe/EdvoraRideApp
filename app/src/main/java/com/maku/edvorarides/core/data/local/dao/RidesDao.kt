package com.maku.edvorarides.core.data.local.dao

import androidx.room.*
import com.maku.edvorarides.core.data.local.models.RideResponse
import com.maku.edvorarides.core.data.local.models.RideResponseItem
import io.reactivex.Flowable

@Dao
interface RidesDao {
    @Transaction
    @Query("SELECT * FROM ride ORDER BY id DESC")
    fun getRides(): Flowable<List<RideResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRides(
        cachedPopular: List<RideResponseItem>
    )

    @Query("UPDATE ride SET distance =:distance WHERE id = :id")
    suspend fun updateRide(distance: String, id: Int)
}