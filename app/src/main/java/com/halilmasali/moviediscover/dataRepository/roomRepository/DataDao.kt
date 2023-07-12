package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {
    @Query("SELECT * FROM local_movie_data")
    fun getAllData(): Array<LocalMovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(localMovieData: LocalMovieData)
}