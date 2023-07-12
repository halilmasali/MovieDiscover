package com.halilmasali.moviediscover.dataRepository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.halilmasali.moviediscover.Constants
import com.halilmasali.moviediscover.dataRepository.apiRepository.ApiConnection
import com.halilmasali.moviediscover.dataRepository.apiRepository.series.SeriesModelResults
import com.halilmasali.moviediscover.dataRepository.roomRepository.LocalMovieData
import com.halilmasali.moviediscover.dataRepository.roomRepository.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository(private val lifecycleOwner: LifecycleOwner, private val context: Context) {
    private val apiConnection = ApiConnection()
    private val database =
        Room.databaseBuilder(context, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
            .build()

    fun getSeriesAiringToday(): LiveData<SeriesModelResults> {
        val airingTodayLiveData: MutableLiveData<SeriesModelResults> = MutableLiveData()

        apiConnection.getSeriesAiringTodayList().observe(lifecycleOwner) { airingTodayList ->
            if (airingTodayList != null) {
                println("Airing Today: ${airingTodayList.results[0].name}")
                val data = LocalMovieData(
                    1,
                    "SeriesAiringToday",
                    Constants.ExpirationTime,
                    airingTodayList.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
        return airingTodayLiveData
    }

    fun getSeriesOnTheAir() {
        apiConnection.getSeriesOnTheAirList().observe(lifecycleOwner) { onTheAir->
            if (onTheAir != null){
                println("On The Air: ${onTheAir.results[0].name}")
                val data = LocalMovieData(
                    2,
                    "SeriesOnTheAir",
                    Constants.ExpirationTime,
                    onTheAir.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
    }

    fun getSeriesPopular() {
        apiConnection.getSeriesPopularList().observe(lifecycleOwner) { popular->
            if (popular != null){
                println("Series Popular: ${popular.results[0].name}")
                val data = LocalMovieData(
                    3,
                    "SeriesPopular",
                    Constants.ExpirationTime,
                    popular.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
    }

    fun getSeriesTopRated() {
        apiConnection.getSeriesTopRatedList().observe(lifecycleOwner) { topRated->
            if (topRated != null){
                println("Series Top Rated: ${topRated.results[0].name}")
                val data = LocalMovieData(
                    4,
                    "SeriesTopRated",
                    Constants.ExpirationTime,
                    topRated.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
    }

    fun getSeriesSimilar(seriesId: String) {
        apiConnection.getSeriesSimilarList(seriesId).observe(lifecycleOwner) { similar->
            if (similar != null){
                println("Series Similar: ${similar.results[0].name}")
                val data = LocalMovieData(
                    4,
                    "SeriesSimilar",
                    Constants.ExpirationTime,
                    similar.results
                )
                insertDataToCache(data, database)
            } else
                println("Response null")
        }
    }



    fun insertDataToCache(data: LocalMovieData, database: MovieDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            dataDao.insertData(data)
        }
    }

    fun getDataFromCache() {
        val database =
            Room.databaseBuilder(context, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
                .build()
        CoroutineScope(Dispatchers.IO).launch {
            val dataDao = database.dataDao()
            val data = dataDao.getAllData()
            println("Data from cache: ${data[0]}")
            println("Data from cache: ${data[1]}")
        }
    }
}