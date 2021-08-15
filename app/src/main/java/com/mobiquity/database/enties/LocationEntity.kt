package com.mobiquity.database.enties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "first_name") val lat: Double?,
    @ColumnInfo(name = "last_name") val lang: Double?,
    @ColumnInfo(name = "city_name") val city: String?,
)