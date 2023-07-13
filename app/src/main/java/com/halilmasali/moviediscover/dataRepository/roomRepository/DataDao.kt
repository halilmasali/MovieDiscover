package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {
    //Series Query
    @Query("SELECT * FROM local_series_data")
    fun getAllSeriesData(): Array<LocalSeriesData>

    @Query("SELECT * FROM local_series_data WHERE category = :category")
    fun getSeriesDataByCategory(category: String): LocalSeriesData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesData(localSeriesData: LocalSeriesData)

    @Delete
    fun deleteData(data: LocalSeriesData)


    //Movies Query
    @Query("SELECT * FROM local_movies_data")
    fun getAllMoviesData(): Array<LocalMoviesData>

    @Query("SELECT * FROM local_movies_data WHERE category = :category")
    fun getMoviesDataByCategory(category: String): LocalMoviesData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesData(localSeriesData: LocalMoviesData)

    @Delete
    fun deleteData(data: LocalMoviesData)
}