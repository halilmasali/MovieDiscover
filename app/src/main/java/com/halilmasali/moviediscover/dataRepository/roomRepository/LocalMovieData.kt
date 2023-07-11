package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_movie_data")
data class LocalMovieData(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name ="name") val name:String,
    @ColumnInfo(name ="expirationTime") val expirationTime: Long
)
