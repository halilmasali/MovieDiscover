package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults

@Entity(tableName = "local_movie_data")
@TypeConverters(Converters::class)
data class LocalMovieData(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "category") val category:String,
    @ColumnInfo(name ="expiration_time") val expirationTime: Long,
    @ColumnInfo(name ="data") val data: ArrayList<SeriesModelResults>? = null,
)
