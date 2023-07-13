package com.halilmasali.moviediscover.dataRepository.roomRepository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.halilmasali.moviediscover.dataRepository.apiRepository.movies.MovieModelResults
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromSeriesModelResultsList(data: ArrayList<SeriesModelResults>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toSeriesModelResultsList(data: String?): ArrayList<SeriesModelResults>? {
        val type = object : TypeToken<ArrayList<SeriesModelResults>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromMoviesModelResultsList(data: ArrayList<MovieModelResults>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toMoviesModelResultsList(data: String?): ArrayList<MovieModelResults>? {
        val type = object : TypeToken<ArrayList<MovieModelResults>>() {}.type
        return gson.fromJson(data, type)
    }
}