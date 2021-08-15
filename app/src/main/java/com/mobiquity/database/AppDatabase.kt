package com.mobiquity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobiquity.database.daos.LocationDao
import com.mobiquity.database.enties.LocationEntity

@Database(entities = arrayOf(LocationEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}