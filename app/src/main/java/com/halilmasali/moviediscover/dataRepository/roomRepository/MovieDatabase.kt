package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [LocalSeriesData::class,LocalMoviesData::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        const val DATABASE_NAME = "movie_database"
    }
}