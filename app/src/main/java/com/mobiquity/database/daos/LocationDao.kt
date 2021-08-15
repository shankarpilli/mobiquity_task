package com.mobiquity.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mobiquity.database.enties.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM locationEntity")
    fun getAll(): List<LocationEntity>

    @Insert
    fun insertAll(vararg users: LocationEntity)

    @Delete
    fun delete(user: LocationEntity)
}
