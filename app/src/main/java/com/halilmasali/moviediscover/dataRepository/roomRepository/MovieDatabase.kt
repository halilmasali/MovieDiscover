package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMovieData::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        const val DATABASE_NAME = "movie_database"
    }
}